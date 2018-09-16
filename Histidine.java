import javax.swing.*;
public class Histidine extends RotatedCircle{
	private float[] CE1 = new float[3];
	private float[] NE2 = new float[3];
	private float[] CD2 = new float[3];
	private float[] ND1 = new float[3];
	private float[] CG = new float[3];
	private float[] CB = new float[3];
	private float[] CA = new float[3];
	private float[] originalCB = new float[3];
	private float[] cir1 = new float [3 * repeats];
	private float[] cir2 = new float [3 * repeats];
	private float[] cir3 = new float [3 * repeats];
	private float[] cir4 = new float [3 * repeats * repeats];
	private float[] smallUnit = new float[3];
	private float[] bigUnit = new float[3];
	private double smallRad = 0;
	private float[][] solution = new float[4][3];
	private double solutionDev = 0;
	private float[] zinc = new float[3];
	private float[] NE2Circle = new float[3*repeats*repeats];
	private float[] CE1Circle = new float[3*repeats*repeats];
	private float[] CD2Circle = new float[3*repeats*repeats];
	private float[] CGCircle = new float[3*repeats*repeats];
	private float[] ND1Circle = new float[3*repeats*repeats];
	private float[] straightVectors = new float[3*repeats*repeats];
	private float [] linearCG = new float[3];
	private float[] CE1Point = new float[3];
	private float[] CD2Point = new float[3];
	private float[] CGPoint = new float[3];
	private float[] ND1Point = new float[3];
	private float[] vectorPoint = new float[3];
	private int solCount = 0;
	private float[][] pentagon = new float[4][3];
	private float[] cirCB1 = new float[3];
	private float[] cirCB2 = new float[3];
	private float[] cirCB3 = new float[3];
	private final double RATIO_LENGTH = 1;
	private final double RATIO_ANGLE = 1.2;
	private final double ERROR_MARGIN = Math.PI/360;
	
	public Histidine (float[] atom1, float[] atom2, float[] atom3, float[] atom4, float[] rotate1, float[] rotate2, float[] end, float[][] array1, float[][] array2, float[][] array3, float[] CB1, float[] CB2, float[] CB3) {
		for (int n = 0; n < 3; n++)
			originalCB[n] = rotate2[n]; 
		for (int n = 0; n < 3; n++)
			CE1[n] = atom1[n] - originalCB[n]; 
		for (int n = 0; n < 3; n++)
			NE2[n] = atom2[n] - originalCB[n];
		for (int n = 0; n < 3; n++)
			CD2[n] = atom3[n] - originalCB[n];
		for (int n = 0; n < 3; n++)
			ND1[n] = atom4[n] - originalCB[n];
		for (int n = 0; n < 3; n++)
			CG[n] = rotate1[n] - originalCB[n];
		for (int n = 0; n < 3; n++)
			CB[n] = rotate2[n] - originalCB[n];
		for (int n = 0; n < 3; n++)
			CA[n] = end[n] - originalCB[n];
		for (int c = 0; c < repeats; c++)
			for (int r = 0; r < 3; r++)
				cir1 [r+3*c] = array1[r][c];
		for (int c = 0; c < repeats; c++)
			for (int r = 0; r < 3; r++)
				cir2 [r+3*c] = array2[r][c];
		for (int c = 0; c < repeats; c++)
			for (int r = 0; r < 3; r++)
				cir3 [r+3*c] = array3[r][c];
		bigUnit = unitVector(CB,CA);
		cirCB1 = CB1;
		cirCB2 = CB2;
		cirCB3 = CB3;
		for (int big = 0; big < repeats; big++) {
			changeRad();
			changeRD(bigUnit,rad);
			float[][] copyCG = new float[3][1];
			copyCG[0][0] = CG[0];
			copyCG[1][0] = CG[1];
			copyCG[2][0] = CG[2];
			copyCG = multMatrix(copyCG);
			linearCG [0] = copyCG [0][0];
			linearCG [1] = copyCG [1][0];
			linearCG [2] = copyCG [2][0];
			float[][] copyNE2 = new float[3][1];
			copyNE2[0][0] = NE2[0];
			copyNE2[1][0] = NE2[1];
			copyNE2[2][0] = NE2[2];
			copyNE2 = multMatrix(copyNE2);
			float[][] copyCE1 = new float[3][1];
			copyCE1[0][0] = CE1[0];
			copyCE1[1][0] = CE1[1];
			copyCE1[2][0] = CE1[2];
			copyCE1 = multMatrix(copyCE1);
			float[][] copyCD2 = new float[3][1];
			copyCD2[0][0] = CD2[0];
			copyCD2[1][0] = CD2[1];
			copyCD2[2][0] = CD2[2];
			copyCD2 = multMatrix(copyCD2);
			float[][] copyND1 = new float[3][1];
			copyND1[0][0] = ND1[0];
			copyND1[1][0] = ND1[1];
			copyND1[2][0] = ND1[2];	
			copyND1 = multMatrix(copyND1);
			for (int small = 0; small < repeats; small++){
				smallUnit = unitVector(linearCG,CB);
				changeSmallRad();
				changeRD(smallUnit,smallRad);
				float [][] tempArray = new float[3][1];
				tempArray = multMatrix(copyNE2);
				NE2Circle[3*big*repeats+3*small] = tempArray [0][0];
				NE2Circle[3*big*repeats+3*small+1] = tempArray [1][0];
				NE2Circle[3*big*repeats+3*small+2] = tempArray [2][0];
				float[][] NE2Pt = tempArray;
				tempArray = multMatrix(copyCE1);
				float[][] CE1Pt = tempArray;
				CE1Circle[3*big*repeats+3*small] = tempArray [0][0];
				CE1Circle[3*big*repeats+3*small+1] = tempArray [1][0];
				CE1Circle[3*big*repeats+3*small+2] = tempArray [2][0];
				tempArray = multMatrix(copyCD2);
				float[][] CD2Pt = tempArray;
				CD2Circle[3*big*repeats+3*small] = tempArray [0][0];
				CD2Circle[3*big*repeats+3*small+1] = tempArray [1][0];
				CD2Circle[3*big*repeats+3*small+2] = tempArray [2][0];
				tempArray = multMatrix(copyND1);
				ND1Circle[3*big*repeats+3*small] = tempArray [0][0];
				ND1Circle[3*big*repeats+3*small+1] = tempArray [1][0];
				ND1Circle[3*big*repeats+3*small+2] = tempArray [2][0]; 	
				
				CGCircle[3*big*repeats+3*small] = linearCG[0];
				CGCircle[3*big*repeats+3*small+1] = linearCG[1];
				CGCircle[3*big*repeats+3*small+2] = linearCG[2];
				float[] CE1Vector = findVector(CE1Pt, NE2Pt);
				float[] CD2Vector = findVector(CD2Pt, NE2Pt);
				float[] tempVectorSum = addVector(CE1Vector, CD2Vector);
				straightVectors[3*big*repeats+3*small] = tempVectorSum[0];
				straightVectors[3*big*repeats+3*small+1] = tempVectorSum[1];
				straightVectors[3*big*repeats+3*small+2] = tempVectorSum[2];
			}
		}	
		equiDistant(NE2Circle);
	}
	
	public float[] findVector (float[][] start, float[][] end) {
		float[] vector = new float[3];
		vector[0] = end[0][0] - start[0][0];
		vector[1] = end[1][0] - start[1][0];
		vector[2] = end[2][0] - start[2][0];
		return vector;
	}
	
	public float[] unitVector (float[] front, float[] back) { // closer to ring = front
		float[] unitVector = new float[3];
		float vectorDist = distance(front,back);
		unitVector[0] = (front[0] - back [0])/vectorDist;
		unitVector[1] = (front [1] - back [1])/vectorDist;
		unitVector[2] = (front [2] - back [2])/vectorDist;
		return unitVector;
	}
	/** 
	 * returns a unit vector after adding two vectors
	 * @param vector1
	 * @param vector2
	 * @return sum of vectors
	 */
	public float[] addVector(float[] vector1, float[] vector2) {
		float [] vectorSum = new float[3];
		vectorSum[0] = vector1[0] + vector2[0];
		vectorSum[1] = vector1[1] + vector2[1];
		vectorSum[2] = vector1[2] + vector2[2];
		float vectorDist = distance(vectorSum);
		vectorSum[0] = vectorSum[0]/vectorDist;
		vectorSum[1] = vectorSum[1]/vectorDist;
		vectorSum[2] = vectorSum[2]/vectorDist;
		return vectorSum;
	}
	public void changeSmallRad () {
		smallRad = smallRad + 2*Math.PI/repeats;
	}
	public void changeRD (float[] unitVector, double rad) {
		RD [0][0] = Math.cos(rad) - (Math.cos(rad) - 1) * unitVector[0] * unitVector[0];
		RD [0][1] = (1 - Math.cos(rad)) * unitVector[0] * unitVector[1] - (Math.sin(rad)) * unitVector[2];
		RD [0][2] = Math.sin(rad) * unitVector[1] - (Math.cos(rad) - 1) * unitVector[0] * unitVector[2];
		RD [1][0] = (1 - Math.cos(rad))*unitVector[0]*unitVector[1] + Math.sin(rad)*unitVector[2];
		RD [1][1] = Math.cos(rad) - (Math.cos(rad) - 1)*unitVector[1]*unitVector[1];
		RD [1][2] = -Math.sin(rad)*unitVector[0] - (Math.cos(rad) - 1)*unitVector[1]*unitVector[2];
		RD [2][0] = -Math.sin(rad)*unitVector[1] - (Math.cos(rad) - 1)*unitVector[0]*unitVector[2];
		RD [2][1] = Math.sin(rad)*unitVector[0] - (Math.cos(rad) - 1)*unitVector[1]*unitVector[2];
		RD [2][2] = Math.cos(rad) - (Math.cos(rad) - 1)*unitVector[2]*unitVector[2];
	}
	public float[][] multMatrix (float[][] point) {
		float[][] ans = new float[3][1];
		for (int r =0; r < 3; r++)
				ans[r][0] = 0;
			for (int r=0; r < 3; r++) {
				ans[r][0] = (float) ((RD[r][0]*point[0][0] + RD[r][1]*point[1][0] + RD[r][2]*point[2][0])); 
			}
		return ans;
	}				
	public void equiDistant (float[] cir4) {
		float[] firstPoint = new float[3];
		float[] secondPoint = new float[3];
		float[] thirdPoint = new float[3];
		float[] fourthPoint = new float[3];
		double dev = 10000;
		double newDev = 0;
		for (int r = 0; r < 3; r++) {
			firstPoint[r] = 0;
			secondPoint[r] = 0; 
			thirdPoint[r] = 0;
			fourthPoint[r] = 0; 
		}
		
		for (int Pt1 = 0; 3*Pt1 < 3*repeats; Pt1++) {
			firstPoint[0] = cir1[3*Pt1];
			firstPoint[1] = cir1[3*Pt1+1];
			firstPoint[2] = cir1[3*Pt1+2];
			for (int Pt2 = 0; 3*Pt2 < 3*repeats; Pt2++ ) {
				secondPoint[0] = cir2[3*Pt2];
				secondPoint[1] = cir2[3*Pt2+1];
				secondPoint[2] = cir2[3*Pt2+2];
				for (int Pt3 = 0; 3*Pt3 < 3*repeats; Pt3++) {
					thirdPoint[0] = cir3[3*Pt3];
					thirdPoint[1] = cir3[3*Pt3+1];
					thirdPoint[2] = cir3[3*Pt3+2];
					for (int Pt4 = 0; 3*Pt4 < 3* repeats * repeats; Pt4++ ) {
						fourthPoint[0] = cir4[3*Pt4] + originalCB[0];
						fourthPoint[1] = cir4[3*Pt4+1] + originalCB[1];
						fourthPoint[2] = cir4[3*Pt4+2] + originalCB[2];
						
						CE1Point[0] = CE1Circle[3*Pt4] + originalCB[0];
						CE1Point[1] = CE1Circle[3*Pt4+1] + originalCB[1];
						CE1Point[2] = CE1Circle[3*Pt4+2] + originalCB[2];
						
						CD2Point[0] = CD2Circle[3*Pt4] + originalCB[0];
						CD2Point[1] = CD2Circle[3*Pt4+1] + originalCB[1];
						CD2Point[2] = CD2Circle[3*Pt4+2] + originalCB[2];
						
						CGPoint[0] = CGCircle[3*Pt4] + originalCB[0];
						CGPoint[1] = CGCircle[3*Pt4+1] + originalCB[1];
						CGPoint[2] = CGCircle[3*Pt4+2] + originalCB[2];
						
						ND1Point[0] = ND1Circle[3*Pt4] + originalCB[0];
						ND1Point[1] = ND1Circle[3*Pt4+1] + originalCB[1];
						ND1Point[2] = ND1Circle[3*Pt4+2] + originalCB[2];
						
						vectorPoint[0] = straightVectors[3*Pt4];
						vectorPoint[1] = straightVectors[3*Pt4+1];
						vectorPoint[2] = straightVectors[3*Pt4+2];
						
						newDev = upDeviation(firstPoint, secondPoint, thirdPoint, fourthPoint);
						
						if (dev > newDev) {
							for (int column =0; column<3; column++) {
								solution[0][column] = firstPoint[column];
								solution[1][column] = secondPoint[column];
								solution[2][column] = thirdPoint[column];
								solution[3][column] = fourthPoint[column];
							}	
							pentagon[0][0] = CE1Point[0]; // CE1 save
							pentagon[0][1] = CE1Point[1];
							pentagon[0][2] = CE1Point[2];
							
							pentagon[1][0] = CD2Point[0]; // CD2 save
							pentagon[1][1] = CD2Point[1];
							pentagon[1][2] = CD2Point[2];
							
							pentagon[2][0] = CGPoint[0]; // CG save
							pentagon[2][1] = CGPoint[1];
							pentagon[2][2] = CGPoint[2];

							pentagon[3][0] = ND1Point[0]; // ND1 save
							pentagon[3][1] = ND1Point[1];
							pentagon[3][2] = ND1Point[2];
							
							solCount = Pt4 +41;
							dev = newDev;
						}
					}
				}	
			}
		}	
	solutionDev = dev;
	}
		
private double upDeviation (float[] a, float[] b, float[] c, float[] d) {
	for (int i =0; i < 3; i++) {     // find the averages of the points for zinc
		zinc[i] = (a[i] + b[i] + c[i] + d[i])/4;
	}
	float[] zincVector = new float[3];
	float vectorDist = distance(zinc, d);
	for (int i = 0; i < 3; i++) {
		zincVector[i] = (zinc[i] - d[i])/vectorDist; 
	}
	float testDist = distance(zincVector, vectorPoint);
	double testAngle = findAngle(1.0f,1.0f, testDist);
	if (testAngle > ERROR_MARGIN) {
		double fail = 10000;
		return fail;
	}
	double ang1 = testAngle(a,cirCB1);
	double ang2 = testAngle(b,cirCB2);
	double ang3 = testAngle(c,cirCB3);
	
	float dist1 = RotatedCircle.distance(a,b);
	float dist2 = RotatedCircle.distance(a,c);
	float dist3 = RotatedCircle.distance(a,d);
	float dist4 = RotatedCircle.distance(b,c);
	float dist5 = RotatedCircle.distance(b,d);
	float dist6 = RotatedCircle.distance(c,d);
	float mean = (dist1 + dist2 + dist3 + dist4 + dist5 + dist6)/6;
	
	float comp1 = propSquare(mean, dist1);
	float comp2 = propSquare(mean, dist2);
	float comp3 = propSquare(mean, dist3);
	float comp4 = propSquare(mean, dist4);
	float comp5 = propSquare(mean, dist5);
	float comp6 = propSquare(mean, dist6);
	
	double deviation = RATIO_LENGTH*Math.sqrt((comp1 + comp2 + comp3 + comp4 + comp5 + comp6)/6) + RATIO_ANGLE*(((ang1/(2*Math.PI/3)) + (ang2/(2*Math.PI/3)) + (ang3/(2*Math.PI/3))/4));
	return deviation;
}
private double testAngle (float[] SG, float[] CB) {
	float testDist1 = RotatedCircle.distance(SG,CB);
	float testDist2 = RotatedCircle.distance(SG, zinc);
	float testDist3 = RotatedCircle.distance(CB, zinc);
	double angle = Math.abs(((2*Math.PI/3)-(findAngle(testDist1, testDist2, testDist3))));
	return angle;
}
private float propSquare (float mean, float num2) {
	float result = ((mean - num2)/mean) * ((mean - num2)/mean);
	return result;
}
public void display (JTextArea a) { //comes out in order of StDev, CA1, CA2, CA3, CE1, NE2, CD2, CG, ND1, ZN
	StringBuilder sb = new StringBuilder();
	sb.append(Double.toString(solutionDev));
	sb.append(",");
	for (int c =0; c <3; c++) {
	sb.append(Float.toString(solution[0][c]));
	sb.append(",");
	}
	for (int c =0; c <3; c++) {
		sb.append(Float.toString(solution[1][c]));
		sb.append(",");
		}
	for (int c =0; c <3; c++) {
		sb.append(Float.toString(solution[2][c]));
		sb.append(",");
		}
	for (int c =0; c <3; c++) {
		sb.append(Float.toString(pentagon[0][c]));
		sb.append(",");
		}	
	for (int c =0; c <3; c++) {
		sb.append(Float.toString(solution[3][c]));
		sb.append(",");
		}
	for (int c =0; c <3; c++) {
		sb.append(Float.toString(pentagon[1][c]));
		sb.append(",");
		}
	for (int c =0; c <3; c++) {
		sb.append(Float.toString(pentagon[2][c]));
		sb.append(",");
		}

	for (int c =0; c <3; c++) {
		sb.append(Float.toString(pentagon[3][c]));
		sb.append(",");
		}
	for (int i =0; i < 3; i++) {     // find the averages of the points for zinc
		zinc[i] = (solution[0][i] + solution[1][i] + solution[2][i] + solution[3][i])/4;
	}
	for (int n = 0; n < 3; n++) {
	sb.append(Float.toString(zinc[n]));
	sb.append(",");
	}
	sb.deleteCharAt(sb.length()-1);
	a.setText("" + sb);
}
public void displayDist (JTextArea b) {
	StringBuilder sbDist = new StringBuilder();
	float solDist1 = 0;
	float solDist2 = 0;
	float solDist3 = 0;
	float solDist4 = 0;
	float [] tempSol1 = new float[3];
	float [] tempSol2 = new float[3];
	float [] tempSol3 = new float[3];
	float [] tempSol4 = new float[3];
	for (int r =0; r <3; r++) {
		tempSol1[r] = solution[0][r];
		tempSol2[r] = solution[1][r];
		tempSol3[r] = solution[2][r];
		tempSol4[r] = solution[3][r];
	}
	solDist1 = RotatedCircle.distance(tempSol1, zinc);
	solDist2 = RotatedCircle.distance(tempSol2, zinc);
	solDist3 = RotatedCircle.distance(tempSol3, zinc);
	solDist4 = RotatedCircle.distance(tempSol4, zinc);
	sbDist.append(Float.toString(solDist1));
	sbDist.append(",");
	sbDist.append(Float.toString(solDist2));
	sbDist.append(",");
	sbDist.append(Float.toString(solDist3));
	sbDist.append(",");
	sbDist.append(Float.toString(solDist4));
	b.setText("" + sbDist);
}
}

