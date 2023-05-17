To run the code follow the steps
step 1: pip install mpi4py numpy
step 2: mpirun -np <number_of_processors> python DistributedSum.py
        Replace <number_of_processors> with the desired number of MPI processes you want to run.
        Eg: mpirun -np 4 python DistributedSum.py
        
Explanation of output: the output of 5050 is correct for the given code. The code is designed to calculate the sum of numbers from 1 to n using MPI (Message Passing Interface) for distributed computing.

In this case, the input array contains numbers from 1 to 100, and the code distributes the array elements among multiple processes using the Scatter() function. Each process receives a portion of the array and calculates the sum of its local elements. Then, the local sums are collected and reduced to a final sum using the Allreduce() function.

The expected output for an input array containing numbers from 1 to 100 is indeed 5050, which represents the sum of all the numbers in the array.

Therefore, the code is functioning correctly, and the output of 5050 is expected.
   
