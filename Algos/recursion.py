
# def sigma(numInput):
#     #BASE CASE
#     if numInput == 1:
#         return 1
#     else:
#         #implement forward progress by recursively calling the function itself with input(s) that take us one step closer to the base case
#         return numInput + sigma(numInput-1)

# print(sigma(4)) #4+3+2+1 = 10
# print(sigma(3)) #3+2+1 = 6
# print(sigma(2)) #2+1 = 3
# print(sigma(1)) #1   BASE CASE

def sigma(num):
    if num == 1:
        return 1
    else:
        return num + sigma(num - 1)

print(sigma(3))


# “ Recursive Fibonacci
# Write rFib(num). Recursively compute and return numth Fibonacci value. As earlier, treat first two (num = 0, num = 1) Fibonacci vals as 0 and 1. 
# Examples: rFib(2) = 1 (0+1); rFib(3) = 2 (1+1); rFib(4) = 3 (1+2); rFib(5) = 5 (2+3). rFib(3.65) = rFib(3) = 2, rFib(-2) = rFib(0) = 0.”

# Excerpt From: Martin Puryear. “Algorithm Challenges: E-book for Dojo Students.” iBooks. 

import math

def rFib(input):
    num = math.floor(input)
    if num < 0:
        return 0
    if num == 0:
        return 0
    if num == 1:
        return 1
    else:
        return rFib(num - 1) + rFib(num - 2)

print(rFib(5.9))

