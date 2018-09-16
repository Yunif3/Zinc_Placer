import java.awt.*;
import java.util.logging.Logger;

import javax.swing.JTextArea;
public class Connector {
	private int repeats = 60;
	private float [] cir1 = new float [repeats*3];
	private float [] cir2 = new float [repeats*3];
	private float [] cir3 = new float [repeats*3];
	private float [] cir4 = new float [repeats*3];
	private float [] CB1 = new float[3];
	private float [] CB2 = new float[3];
	private float [] CB3 = new float[3];
	private float [] CB4 = new float[3];
	private float [][] solution = new float [5][3];
	float zinc[] = new float[3];
	private final double RATIO_LENGTH = 1;
	private final double RATIO_ANGLE = 1.2;
	private float solutionDist =0;
	private double solutionDev = 0;
	private int x =0;

	public Connector (float[][] array1, float[][] array2, float[][] array3, float[][] array4, float[] initCB1, float[] initCB2, float[] initCB3, float[] initCB4) {
		for (int c = 0; c < repeats; c++)
			for (int r = 0; r < 3; r++)
				cir1 [r+3*c] = array1[r][c];
		for (int c = 0; c < repeats; c++)
			for (int r = 0; r < 3; r++)
				cir2 [r+3*c] = array2[r][c];
		for (int c = 0; c < repeats; c++)
			for (int r = 0; r < 3; r++)
				cir3 [r+3*c] = array3[r][c];
		for (int c = 0; c < repeats; c++)
			for (int r = 0; r < 3; r++)
				cir4 [r+3*c] = array4[r][c];
		CB1 = initCB1;
		CB2 = initCB2;
		CB3 = initCB3;
		CB4 = initCB4;
		equiDistant();
	}

	public void equiDistant () {
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
					for (int Pt4 = 0; 3*Pt4 < 3*repeats; Pt4++ ) {
						fourthPoint[0] = cir4[3*Pt4];
						fourthPoint[1] = cir4[3*Pt4+1];
						fourthPoint[2] = cir4[3*Pt4+2];
						newDev = deviation(firstPoint, secondPoint, thirdPoint, fourthPoint);
						if (dev > newDev) {
							for (int column =0; column<3; column++) {
								solution[0][column] = firstPoint[column];
								solution[1][column] = secondPoint[column];
								solution[2][column] = thirdPoint[column];
								solution[3][column] = fourthPoint[column];
								solution[4][column] = zinc[column];
							}	
							dev = newDev;
						}
					}
				}
			}
		}
		solutionDev = dev;
	}
	private double deviation (float [] a, float[] b, float[] c, float[] d) {
		for (int i =0; i < 3; i++) {     // find the averages of the points for zinc
			zinc[i] = (a[i] + b[i] + c[i] + d[i])/4;
		}
		double ang1 = testAngle(a,CB1);
		double ang2 = testAngle(b,CB2);
		double ang3 = testAngle(c,CB3);
		double ang4 = testAngle(d,CB4);
		float dist1 = RotatedCircle.distance(a,b);
		float dist2 = RotatedCircle.distance(a,c);
		float dist3 = RotatedCircle.distance(a,d);
		float dist4 = RotatedCircle.distance(b,c);
		float dist5 = RotatedCircle.distance(b,d);
		float dist6 = RotatedCircle.distance(c,d);
		float mean = (dist1 + dist2 + dist3 + dist4 + dist5 + dist6)/6;
		float comp1 = RotatedCircle.subSquare(mean, dist1);
		float comp2 = RotatedCircle.subSquare(mean, dist2);
		float comp3 = RotatedCircle.subSquare(mean, dist3);
		float comp4 = RotatedCircle.subSquare(mean, dist4);
		float comp5 = RotatedCircle.subSquare(mean, dist5);
		float comp6 = RotatedCircle.subSquare(mean, dist6);
		double deviation = RATIO_LENGTH*Math.sqrt((comp1 + comp2 + comp3 + comp4 + comp5 + comp6)/6) + RATIO_ANGLE*(((ang1/(2*Math.PI/3)) + (ang2/(2*Math.PI/3)) + (ang3/(2*Math.PI/3)) + (ang4/(2*Math.PI/3)))/4);
		return deviation;
	}
	private double testAngle (float[] SG, float[] CB) {
		float testDist1 = RotatedCircle.distance(SG,CB);
		float testDist2 = RotatedCircle.distance(SG, zinc);
		float testDist3 = RotatedCircle.distance(CB, zinc);
		double angle = Math.abs(((2*Math.PI/3)-(findAngle(testDist1, testDist2, testDist3))));
		return angle;
	}
	public double findAngle (float a, float b, float c) {
		double angle = Math.acos(((c * c) - (a * a) - (b * b)) / (-2 * a * b));
		return angle;
	}
	
	public void Jdisplay(JTextArea a) {
		int y = 100;
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
			sb.append(Float.toString(solution[3][c]));
			sb.append(",");
			} 
		for (int n = 0; n < 3; n++) {
		sb.append(Float.toString(solution[4][n]));
		sb.append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		a.setText("" + sb);
	}
}