import hashlib
import re
import subprocess
import requests

# Function to compute MD5 checksum of a file
def compute_md5(file_path):
    output = subprocess.run(f"md5sum {file_path}", stdout=subprocess.PIPE, shell=True)
    return output.stdout.decode().split()[0]

# Path to the local file
app_files_path = ["meta-hailo-tappas/recipes-gstreamer/tappas-apps/files/download_reqs_imx8.txt"]

for local_file_path in app_files_path:
# Read the content of the local file
    new_files = []
    with open(local_file_path, 'r') as file:
        content = file.read()
    for line in content.splitlines():
        if line == "":
            continue
        if "lpr.raw" in line:
            new_files.append(line)
            continue
        print(line)
        # Extract all URLs from the content
        url = re.findall(r'(https?://[^\s]+)', line)[0]
        file_name = url.split("/")[-1]
        response = requests.get(url, stream=True)

        if response.status_code == 200:
            with open(file_name, "wb") as file:
                for chunk in response.iter_content(chunk_size=8192):
                    file.write(chunk)
            
            new_md5 = compute_md5(file_name)
            line_list = line.split(" -> ")
            line_list[2] = f"{new_md5}"
            new_files.append(" -> ".join(line_list))
            
    output = "\n".join(new_files)
    # Save the updated content to a new file (or overwrite the original, as you prefer)
    output_file_path = local_file_path + "_new"
    with open(output_file_path, "w") as updated_file:
        updated_file.write(output)

    print(f"Processing completed. Check {output_file_path} for the updated MD5 checksums.")
