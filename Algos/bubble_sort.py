# iterates over given list as many times as it takes to sort from least to greatest value

x = [5,3,1,9,6,2,8,7,4]

def bubbleSort(input_list):
    for i in range(len(input_list) - 1):
        for j in range(len(input_list) - 1 - i):
            if input_list[j] > input_list[j + 1]:
                # print(i,j,input_list[j], input_list[j+1])
                input_list[j], input_list[j + 1] = input_list[j + 1], input_list[j]
    return input_list

print(bubbleSort(x))