#imports
from mpi4py import MPI
import numpy as np;

def distribute_elements(array, comm):
    n = len(array)
    local_n = n // comm.Get_size()
    local_array = np.empty(local_n, dtype=int)
    comm.Scatter(array.tobytes(), local_array, root=0)
    local_sum = np.sum(local_array)
    return local_sum

if __name__ == '__main__':
    comm = MPI.COMM_WORLD #initialize the mpi
    rank = comm.Get_rank()

    if rank == 0:
        n = 100
        array = np.arange(1, n+1)
    else:
        array = None

    array = comm.bcast(array, root=0)

    local_sum = distribute_elements(array, comm)

    all_sum = comm.gather(local_sum, root=0)

    if rank == 0:
        total_sum = np.sum(all_sum)
        print("Total sum is:", total_sum)