# Zinc_placer_research
Used to find the location of the zinc atom in a zinc-finger motif of a molecule.
It can be used on both 3 Cysteine - 1 Histidine and 4 Cysteine zinc fingers.
It takes in the 3D coordinates of the atoms that create the ligands which will bond to the zinc,
and then all combinations of the rotated positions, using Rodrigues'rotation formula, will be 
considered to create the most accurate tetrahedral bond with the zinc molecule that 
will be placed at the center

# DIRECTIONS
1. Download the zip file containing four .java files
2. Install Java on the computer if it isn’t installed already, and compile the files using IDE such as Eclipse(www.eclipse.org). In case of Eclipse, create a new Java Project and copy the .java files into the src folder.*
3. From a molecule viewer such as Coot, identify the residue name, chain identifier, and residue sequence number for the Cysteine or Histidine ligands that form a zinc finger (e.g. CYS A 76). 
4. Open up the PDB file you wish to work on as a TEXT file to find and record the coordinates of the atoms in Cysteine ligands or the atoms in Histidine. For the Cysteine atoms, only the x,y,z coordinates of  CA, CB, SG atoms are necessary. For Histidine, the x,y,z coordinates of CE1, NE2, CD2, ND1, CG, CB, CA atoms are required. These will be later inputted to the program. 

(Example 1)	

ATOM   1099  CA  CYS A  76     56.145 (x coord)   4.436 (y) 27.057 (z) 1.00  0.00       	C  

(As appeared on PDB. Bolded parts are the necessary information)

5. Run the NewUI file, and “enter 1 if it's CYS3 - HIS. If it's CYS4, enter 0.” should appear on the console. This asks if the ligands are all Cysteines or if it contains Histidine.
6. As requested by the console, copy and paste the coordinates for each atom (the bolded numbers from example 1) into console, leaving the space between the x,y,z coordinates. Then press enter.
7. Once all coordinates have been inputted, the program will take upto 5 minutes to compute and open a new window when it finishes.
8. Copy the output data from the textbox and paste it into a Excel document. Then use the “Text to Columns” function under Data Tab to delimit the data into cells. 
9. In the case of CYS4, the number in the first cell is the deviation from perfect tetrahedral bond. The 2nd to 4th data show the new x,y,z coordinate of the CA atom from the first ligand that was inputted. The same goes for data 5-7, 8-10, 11-13, showing the new coordinates of CA in the order they were inputted. The last three data (14-16) show the coordinates of the zinc atom associated with the ligands.
10. In the case of CYS3-HIS,  the number in the first cell is the deviation from perfect tetrahedral bond. The 2nd to 4th data show the new x,y,z coordinate of the CA atom from the first ligand that was inputted. The same goes for data 5-7, 8-10, showing the new coordinates of CA in the order they were inputted. Then the rest of the data in groups of 3s represent the x,y,z coordinates of CE1, NE2, CD2, CG, ND1, and zinc atoms respectively.
11. Return to the original PDB text file and replace the old coordinate values of atoms with the new coordinates and save the file.

12. The accuracy of the program can be increased by changing the value of “repeats” variable of the code in RotatedCircle, NewUI, and Connector to a same, but higher value. However, this will cause a longer calculation time. 

*If there is an error like 
Access restriction: The type JTextArea is not accessible due to restriction on required library C:\Program Files (x86)\Java\jre1.8.0_131\lib\rt.jar
https://stackoverflow.com/questions/860187/access-restriction-on-class-due-to-restriction-on-required-library-rt-jar has solutions.
