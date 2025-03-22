#!C:\Apps\Python312 python
# encoding: utf8
import os, sys, re
from urllib.parse import urlparse
def get_name (next_line, index):
    parts=[]
    line = next_line.replace('(', '').replace(')', '').replace('"', '').replace('\r\n', '').replace('\n', '').replace('v2/', '').replace('v1/', '')
    if line.find('?')>0:
        line=line[0:line.find('?')]
    elemts = line.split('/')
    method = elemts[0].strip().replace('.', '')
    for part in elemts:
        if len(part.strip())>0:
            parts.append(part.strip())
    if len(parts)==2:
        if parts[1].find('.')>0:
            name = parts[1]
        else:
            name = "Home"
    else:
        if len(parts)>2:
            name = parts[len(parts)-3] + '/' + parts[len(parts)-2] + '/' + parts[len(parts)-1]
        elif len(parts) > 1:
                name = parts[len(parts) - 2] + '/' + parts[len(parts) - 1]
        else:
            name = parts[len(parts)-1]
    name = str(index).zfill(3) + ':' + method.upper() + ' ' + name.replace('#', '').replace('{', '').replace('}', '')
    return (name)
def rename_queries (sim_file, new_sim_file):
    simu = open(sim_file, 'r')
    new_simu = open(new_sim_file, 'w')
    i=0
    lines = simu.readlines()
    index=0
    while i<len(lines):
        line = lines[i]
        if i<len(lines)-1:
            next_line = lines[i+1]
        else:
            next_line=''
        if line.find('http("')>=0:
            name = get_name (next_line, index)
            line = line.replace('\r\n', '').replace('\n', '')
            parts = line.split('"')
            new_line = '{0}"{1}"{2}'.format(parts[0], name, parts[2])
            print (new_line)
            new_simu.write(new_line +  '\n')
            index+=1
        else:
            new_simu.write(line)
        i+=1
    simu.close()
def change_thinktime(java_file, new_file):
    simu = open(java_file, mode='r', encoding='utf-8')
    out = open(new_file, mode='w', encoding='utf-8')
    i = 0
    lines = simu.readlines()
    index = 0
    while i < len(lines):
        line = lines[i]
        line = line.replace('\r\n', '').replace('\n', '')
        if line.find('.pause(') >= 0:
            pattern = r'\.pause\(\s*\d+(\.\d+)?\s*\)'
            replaced_content = re.sub(pattern, '.pause(thinkTime())', line)
            out.write (replaced_content  + '\n')
            index += 1
        else:
            out.write (line+ '\n')
        i += 1
    simu.close()
    out.close

def main(file_name):
    file_renamed = file_name + '.renamed'
    file_thinktime = file_name + '.thinktime'
    rename_queries (file_name, file_renamed)
    change_thinktime(file_renamed, file_thinktime)
    print (file_renamed)

  
if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("Usage: python script.py <nom_du_fichier>")
    else:
        file_name = sys.argv[1]
        main(file_name)    