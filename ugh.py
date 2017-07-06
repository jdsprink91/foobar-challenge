def total_subsets_matching_sum(numbers, sum):
    array = [1] + [0] * (sum)
    for current_number in numbers:
        print("new iter " + str(current_number))
        for num in xrange(sum - current_number, -1, -1):
            print(num)
            if array[num]:
                array[num + current_number] += array[num]
        print(array);
    return array[sum]

if __name__ == "__main__" :
    total_subsets_matching_sum(range(1, 4), 3)
    '''assert(total_subsets_matching_sum({1, 3, 2, 5, 4, 9}, 9) == 4)'''
