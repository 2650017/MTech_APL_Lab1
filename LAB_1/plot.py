import matplotlib.pyplot as plt
import csv

n = []
time = []

with open("binary_data.csv", "r") as file:

    data = csv.reader(file)
    next(data)

    for row in data:
        n.append(int(row[0]))
        time.append(int(row[1]))

plt.plot(n, time, marker="o")

plt.xlabel("Input Size (n)")
plt.ylabel("Execution Time (ns)")
plt.title("Binary Search Time Complexity")

plt.xscale("log")
plt.grid(True)

plt.show()