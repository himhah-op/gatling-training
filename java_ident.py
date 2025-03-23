java_file = r'C:\github_himhah-op\gatling-training\gatling-java\src\test\java\fr\onepoint\training\OrangeHRM.java'


def replace_key(line, key):
    if line.find("pause")>=0:
        pass
    line = line.replace(f").{key}(", f")\n.{key}(")
    return (line)


def rewrite_line(line):
    line = line.replace('Map.entry(', '\nMap.entry(')
    line = replace_key(line, "get")
    line = replace_key(line, "put")
    line = replace_key(line, "post")
    line = replace_key(line, "delete")
    line = replace_key(line, "body")
    line = replace_key(line, "exec")
    line = replace_key(line, "check")
    line = line.replace('.headers', '\n.headers')
    line = line.replace('.resources(', '\n.resources(')
    line = line.replace('), http(', ')\n, http(')
    return (line)

new_lines=[]
with open(java_file, mode="r", encoding="utf-8") as f:
    lines = f.readlines()
    row=1
    for line in lines:
        line = line.replace('\n', '')
        if len(line.strip()) > 0:
            line = rewrite_line(line)
            new_lines.append(line)
        row+=1
        if row==90:
            pass
for line in new_lines:
    line = line.replace('\n', '')
    if len(line.strip()) > 0:
        print(line)
