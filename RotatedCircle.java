

public class RotatedCircle {
private float [] cA = new float[3];
private float [] cB = new float[3];
private float [] sG = new float[3];
private float xVector = 0;
private float yVector = 0; 
private float zVector = 0;
private float[][] copySG = new float[3][1];
protected double rad = 0;
protected double [][] RD = new double[3][3];
protected int repeats = 60;
	public RotatedCircle() {}
	public RotatedCircle (float [] atom1, float[] atom2, float[] atom3) {
		for (int r = 0; r < 3; r++) {
			cA [r] = 0;
			cB [r] = 0;
			sG [r] = 0;
		}
		for (int r = 0; r < 3; r++) {
			cA [r] = atom1 [r];
			cB [r] = atom2 [r];
			sG [r] = atom3 [r];
		}
	}
	
	public void unitVector () {
		float vectorDist = distance(cB,cA);
		xVector = (cB [0] - cA [0])/vectorDist;
		yVector = (cB [1] - cA [1])/vectorDist;
		zVector = (cB [2] - cA [2])/vectorDist;
	}
	public void translation () {
		// CB' becomes {0,0,0} for reference
		for (int i=0; i < 3; i++)
			copySG[i][0] = sG[i] - cB[i];
	}
	public void changeRad () {
		rad = rad + 2*Math.PI/repeats;
	}
	public void changeRD () {
		RD [0][0] = Math.cos(rad) - (Math.cos(rad) - 1) * xVector * xVector;
		RD [0][1] = (1 - Math.cos(rad)) * xVector * yVector - (Math.sin(rad)) * zVector;
		RD [0][2] = Math.sin(rad) * yVector - (Math.cos(rad) - 1) * xVector * zVector;
		RD [1][0] = (1 - Math.cos(rad))*xVector*yVector + Math.sin(rad)*zVector;
		RD [1][1] = Math.cos(rad) - (Math.cos(rad) - 1)*yVector*yVector;
		RD [1][2] = -Math.sin(rad)*xVector - (Math.cos(rad) - 1)*yVector*zVector;
		RD [2][0] = -Math.sin(rad)*yVector - (Math.cos(rad) - 1)*xVector*zVector;
		RD [2][1] = Math.sin(rad)*xVector - (Math.cos(rad) - 1)*yVector*zVector;
		RD [2][2] = Math.cos(rad) - (Math.cos(rad) - 1)*zVector*zVector;
	}
	public float[][] multMatrix () {
		float[][] ans = new float[3][1];
		for (int r =0; r < 3; r++)
				ans[r][0] = 0;
			for (int r=0; r < 3; r++) {
				ans[r][0] = (float) ((RD[r][0]*copySG[0][0] + RD[r][1]*copySG[1][0] + RD[r][2]*copySG[2][0])); // check what was wrong with this later
			}
		return ans;
	}					
	public float [][] addMatrix (float [][] matrix1, float [][] matrix2) {
		float [][] result = new float[3][1];
		for (int row = 0; row < 3; row++)
			result[row][1] = matrix1[row][1] + matrix2[row][1];
		return result;
	}
	public double findAngle (float a, float b, float c) {
		double angle = Math.acos(((c * c) - (a * a) - (b * b)) / (-2 * a * b));
		return angle;
	}
	public static float distance (float[] array1, float[] array2) {
		float length = (float) Math.sqrt( subSquare (array1[0], array2[0]) + subSquare (array1[1], array2[1]) + subSquare (array1[2], array2[2]));
		return length;
	}
	public static float distance (float[] array1) {
		float length = (float) Math.sqrt(array1[0]*array1[0] + array1[1]*array1[1] + array1[2]*array1[2]);
		return length;
	}
	public static float subSquare (float num1, float num2) {
		float result = (num1 - num2) * (num1 - num2);
		return result;
	}
}


