import numpy as np
import matplotlib.pyplot as plt


File_object = open("subject_response/response_Hanyu Xia_2019_11_16_10_40_35.txt","r")
file_data = File_object.readlines();
file_length = len(file_data)

choice_table = {'Ate' : 0,'It': 0,'Etch': 0,'Axe': 0,'Eat': 0}
Ate = ['Ate','hate','hated','fate','fated']
Axe  = ['Axe','chatted','patted','pat','chat']
Eat = ['Eat','seat','seated','heated','cheated','cheat','heat']
Etch = ['Etch','pet','petted','set']
It = ['It','pit','fitted','pitted','fit']
word_choice = [Ate, Axe, Eat, Etch, It]

def is_correct(word_choice, line_in_txt):
    for item in word_choice:
        for elem in item:
            if(elem == line_in_txt[0]):
                if(line_in_txt[4] == item[0]):
                    return True
                else:
                    return False
    return False

correct = 0
correct_end_t = 0
total_end_t = 0
total = 0

for elem in file_data:
    split_elem = elem.split('\t')
    if(len(split_elem) > 3):
        total += 1
        choice_table[split_elem[4]]+=1
        if(is_correct(word_choice, split_elem)):
            correct += 1




print(choice_table)
print(correct)
print(total)
print(correct/total)

plt.bar(choice_table.keys(), choice_table.values())
plt.show()
