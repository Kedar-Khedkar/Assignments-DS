from mpi4py import MPI

def calculate_partial_sum(arr):
    partial_sum = sum(arr)
    return partial_sum

def distribute_array(arr):
    """
    This function distributes an array to multiple processes, calculates the partial sum for each
    process, gathers all the partial sums at the root process, and displays the intermediate and final
    sums.
    
    :param arr: The input array that needs to be distributed and processed in parallel
    """
    comm = MPI.COMM_WORLD
    size = comm.Get_size()
    rank = comm.Get_rank()

    # Distribute the array to all processes
    chunk_size = len(arr) // size
    remainder = len(arr) % size

    start = rank * chunk_size
    end = start + chunk_size

    if rank == size - 1:
        # Include the remaining elements in the last chunk
        end += remainder

    chunk = arr[start:end]

    # Calculate the partial sum for the chunk
    partial_sum = calculate_partial_sum(chunk)

    # Gather all the partial sums at the root process (rank 0)
    total_sum = comm.reduce(partial_sum, op=MPI.SUM, root=0)

    if rank == 0:
        # Display the intermediate and final sums in order of rank
        for i in range(size):
            print("Rank", i, "Partial Sum:", calculate_partial_sum(arr[i*chunk_size:(i+1)*chunk_size]))
        print("Final Sum:", total_sum)

if __name__ == '__main__':
    arr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]  # Example array
    distribute_array(arr)

