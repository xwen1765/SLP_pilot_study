import numpy as np
import matplotlib.pyplot as plt
import os

# Initialize
ape = ['ape','hate','hated','fate','fated']
axe  = ['axe','chatted','patted','pat','chat']
ease = ['ease','seat','seated','heated','cheated','cheat','heat']
egg = ['egg','pet','petted','set']
itch = ['itch','pit','fitted','pitted','fit']
word_table = {'ape' : 2,'itch': 2,'egg': 2,'axe': 2,'ease': 3};
word_choice = [ape, axe, ease, egg, itch]

def is_correct(word_choice, line_in_txt):
    for item in word_choice:
        for elem in item:
            if(elem == line_in_txt[0]):
                if(line_in_txt[4] == item[0]):
                    return True
                else:
                    return False
    return False

def autolabel(rects):
    """Attach a text label above each bar in *rects*, displaying its height."""
    for rect in rects:
        height = rect.get_height()
        ax.annotate('{}'.format(height),
                    xy=(rect.get_x() + rect.get_width() / 2, height),
                    xytext=(0, 3),  # 3 points vertical offset
                    textcoords="offset points",
                    ha='center', va='bottom')


choice_table = {'ape' : 0,'itch': 0,'egg': 0,'axe': 0,'ease': 0}
number_sub = 0
total_correct = 0

# for each file in this folder
for filename in os.listdir("new_subject_response"):

    if "txt" not in filename:
        continue

    #read data from the file
    sub_name = filename.split('_')[1]
    print("Subject Name: ",sub_name)
    File_object = open("new_subject_response/"+filename,"r")
    file_data = File_object.readlines();
    file_length = len(file_data)

    #collect data for all subject



    # Run accuracy analysis for each subject


    choice_table = {'ape' : 0,'itch': 0,'egg': 0,'axe': 0,'ease': 0}
    choice_correct_table = {'ape' : 0,'itch': 0,'egg': 0,'axe': 0,'ease': 0}
    correct = 0
    total = 0

    for elem in file_data:
        split_elem = elem.split('\t')
        #print(split_elem)
        if(len(split_elem) > 5):
            total += 1
            choice_table[split_elem[4]]+=1
            if(is_correct(word_choice, split_elem)):
                choice_correct_table[split_elem[4]]+=1
                correct += 1

    print("Number of choice for each vowel",choice_table)
    print("Number of correct",correct)
    print("Accuracy",correct/total)
    barWidth = 0.25

    choice_print_table = [];
    for sub in choice_table:
        choice_print_table.append(choice_table[sub]/total);
    word_print_table = [];
    for sub in word_table:
        word_print_table.append(word_table[sub]/11)
    choice_correct_print_table = [];
    for sub in choice_correct_table:
        choice_correct_print_table.append(choice_correct_table[sub]/choice_table[sub])

    print("\n")

    # graphs
    r1 = np.arange(len(choice_table.keys()))
    r2 = [x + barWidth for x in r1]
    plt.bar(r1, choice_print_table, width = 0.25, label="Subject percentage response for each vowel")
    plt.bar(r2, word_print_table, width = 0.25, label = "Actual percentage for each vowel")
    plt.xlabel('Vowel', fontweight='bold')
    plt.title("Subject name:"+sub_name)
    plt.legend()
    plt.xticks([r + barWidth for r in range(len(choice_table.keys()))], ['ape','itch','egg','axe','ease'])
    plt.axis(ymax=0.40)
    plt.savefig("new_graphs/precentage_response"+sub_name+".png")
    plt.show()

    rect = plt.figure()
    plt.bar(r1, choice_correct_print_table, width = 0.75, label="Accuracy for each vowel")
    plt.xlabel('Vowel', fontweight='bold')
    plt.title("Subject name:"+sub_name)
    plt.legend()
    plt.xticks([r for r in range(len(choice_table.keys()))], ['ape','itch','egg','axe','ease'])
    # plt.axis(ymax=0.40)
    plt.savefig("new_graphs/accuracy_each_vowel"+sub_name+".png")
    plt.show()
