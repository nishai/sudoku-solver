import matplotlib.pyplot as plt
import numpy as np

file = 'data_.csv'
data = np.genfromtxt(file, delimiter=',', skip_header=1, names=['clues','average_time','average_backtracks','best_time','best_backtracks','worst_time','worst_backtracks'])

plt.plot(data['clues'], data['average_time'], label="Average time to solve game")
plt.plot(data['clues'], data['worst_time'], label="Longest time to solve game")
plt.plot(data['clues'], data['best_time'], label="Shortest time to solve game")
# plt.plot(data['clues'], data['average_backtracks'], label="Average number of backtracks")
# plt.plot(data['clues'], data['worst_backtracks'], label="Most number of backtracks")
# plt.plot(data['clues'], data['best_backtracks'], label="Least number of backtracks")
plt.xlabel('Number of initial clues')
plt.ylabel('Run time (nanoseconds)')
plt.legend()
plt.title('Number of initial clues vs. run times in best, worst, and average case')
plt.yscale('log')
plt.show()