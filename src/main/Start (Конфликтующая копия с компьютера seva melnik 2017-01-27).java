
package main;

public class Start {

	static int n = 4;
	static int arr[][] = new int[n][n];
	static int currentCell = 0;
	static int currentNum = 1;
	static int currentI = 0;
	static int currentJ = 0;
	static int whiteBlocks = 0;
	static int cornerBlockSize = 0;
	static int centerBlockSize = 0;
	static int[] arrBlocks;
	static int[] allBlocksWithNumbers;
	static int[] arrayOfWhiteNumbers;

	public static void main(String[] args) {

		if (n >= 3) {
			if (n % 2 != 0) {
				even();
			} else {
				odd();
				System.out.println("Треба ще для парних зробити");
			}
		} else {
			System.out.println("Потрібне число більше 2");
		}
	}

	public static void putOne() {
		int cell = n / 2;
		cell++;
		arr[0][cell - 1] = 1;
		currentNum = 2;
		// currentCell=i*n+j+1;
		currentCell = cell;
		currentI = 0;
		currentJ = cell - 1;
	}

	public static void print() {
		int[] ar = new int[n];
		int d1 = 0;
		int d2 = 0;
		int d2i = 0;
		int d2j = n - 1;

		for (int i = 0; i < arr.length; i++) {
			int sum = 0;
			for (int j = 0; j < arr.length; j++) {
				System.out.print(arr[i][j] + "  ");
				sum += arr[i][j];
				ar[j] += arr[i][j];

				if (i == j) { // d1
					d1 += arr[i][j];
				}
				if ((i == d2i) && (j == d2j)) {
					d2 += arr[i][j];
					d2i++;
					d2j--;
				}
			}
			System.out.print("   Sum=  " + sum);
			System.out.println();
		}
		System.out.println();
		for (int i = 0; i < ar.length; i++) {
			System.out.print(ar[i] + " ");

		}
		System.out.println();
		System.out.println("D1 =" + d1);
		System.out.println("D2 =" + d2);
		System.out.println("Sum= " + (((n * n) + 1) * n / 2));

	}

	public static void putNext() {

		int putI = 0;
		int putJ = 0;

		///////////////////////////////////////////////////////

		if ((currentI - 1) >= 0) { //
			putI = currentI - 1;
		} else {
			putI = n - 1;

		}

		if ((currentJ + 1) <= n - 1) {
			putJ = currentJ + 1;
		} else {
			putJ = 0;
		}

		///////////////////////////////////////////////////////

		if (arr[putI][putJ] == 0) {
			arr[putI][putJ] = currentNum;
			currentNum++;
			currentI = putI;
			currentJ = putJ;
		} else {
			putJ = currentJ;
			if ((currentI + 1) <= n - 1) {
				putI = currentI + 1;
				arr[putI][putJ] = currentNum;
				currentNum++;
				currentI = putI;
				currentJ = putJ;
			} else {
				putI = 0;
				arr[putI][putJ] = currentNum;
				currentNum++;
				currentI = putI;
				currentJ = putJ;
			}

		}

	}

	public static void even() {
		putOne();

		for (int i = 0; i < ((n * n) - 1); i++) {
			putNext();
		}
		print();
	}

	public static void odd() {
		if ((n % 2 == 0) && (n % 4 == 0)) {
			System.out.println("Квадрат двойної кратності");
			fillArray();
			findSquare();
			whiteBlocks();
			findNumbersFromBlocks();
			makeArrayOfWhiteNumbersDesc();

		}
	}

	public static void fillArray() {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				arr[i][j] = i * n + j + 1;
			}
		}
		print();
	}

	public static void findSquare() {
		cornerBlockSize = n / 4;
		centerBlockSize = n / 2;

	}

	public static void whiteBlocks() {
		int all = n * n;
		int cornerBlocks = cornerBlockSize * cornerBlockSize * 4;
		int centerBlocks = centerBlockSize * centerBlockSize;
		whiteBlocks = all - cornerBlocks - centerBlocks;
		int[] center = new int[whiteBlocks];

	}

	public static void findNumbersFromBlocks() {
		int[] corn1 = new int[cornerBlockSize * cornerBlockSize];
		int[] corn2 = new int[cornerBlockSize * cornerBlockSize];
		int[] corn3 = new int[cornerBlockSize * cornerBlockSize];
		int[] corn4 = new int[cornerBlockSize * cornerBlockSize];
		int[] center = new int[centerBlockSize * centerBlockSize];
		allBlocksWithNumbers = new int[(centerBlockSize * centerBlockSize) + ((cornerBlockSize * cornerBlockSize) * 4)];

		//////////////////////////// corn1
		int n1 = 0;
		for (int i = 0; i < cornerBlockSize; i++) {
			for (int j = 0; j < cornerBlockSize; j++) {
				corn1[n1] = arr[i][j];
				// System.out.print(corn1[n1] + " ");
				n1++;
			}
		}
		// System.out.println();
		//////////////////////////// corn2
		int n2 = 0;
		for (int i = 0; i < cornerBlockSize; i++) {
			for (int j = n - cornerBlockSize; j < n; j++) {
				corn2[n2] = arr[i][j];
				// System.out.print(corn2[n2] + " ");
				n2++;
			}
		}
		// System.out.println();
		//////////////////////////// corn3
		int n3 = 0;
		for (int i = n - cornerBlockSize; i < n; i++) {
			for (int j = 0; j < cornerBlockSize; j++) {
				corn3[n3] = arr[i][j];
				// System.out.print(corn3[n3] + " ");
				n3++;
			}
		}
		// System.out.println();
		//////////////////////////// corn4
		int n4 = 0;
		for (int i = n - cornerBlockSize; i < n; i++) {
			for (int j = n - cornerBlockSize; j < n; j++) {
				corn4[n4] = arr[i][j];
				// System.out.print(corn4[n4] + " ");
				n4++;
			}
		}
		// System.out.println();

		//////////////////////////// center

		int startI = cornerBlockSize;
		int endI = n - cornerBlockSize;
		int startJ = n / 4;
		int endJ = n / 4 * 3;

		int n5 = 0;
		for (int i = startI; i < endI; i++) {
			for (int j = startJ; j < endJ; j++) {
				center[n5] = arr[i][j];
				// System.out.print(center[n5] + " ");
				n5++;
			}
		}
		// System.out.println();

		///////////////////////////// об'єднуєм кутові в один масив
		int allCornerBlocks[] = new int[cornerBlockSize * cornerBlockSize * 4];
		// for (int i = 0; i < allCornerBlocks.length; i++) {
		for (int j = 0; j < corn1.length; j++) {
			allCornerBlocks[j] = corn1[j];
			// System.out.println(allCornerBlocks[j]);
		}
		int k2 = corn1.length;
		for (int j = k2; j < (corn1.length * 2); j++) {
			allCornerBlocks[j] = corn2[j - corn1.length];
			// System.out.println(allCornerBlocks[j]);
		}

		int k3 = corn1.length * 2;
		for (int j = k3; j < (corn1.length * 3); j++) {
			allCornerBlocks[j] = corn3[j - corn1.length * 2];
			// System.out.println(allCornerBlocks[j]);
		}

		int k4 = corn1.length * 3;
		for (int j = k4; j < (corn1.length * 4); j++) {
			allCornerBlocks[j] = corn4[j - corn1.length * 3];
			// System.out.println(allCornerBlocks[j]);
		}

		/////////////////////////////////////////////////////////////

		// об'єднуєм загальний там де кутові з центральним в одну загальну
		int k5 = corn1.length * 4;
		int s = 0;

		for (int i = 0; i < corn1.length * 4; i++) {
			allBlocksWithNumbers[i] = allCornerBlocks[i];
			s = i;
		}
		// System.out.println("s= "+s);

		for (int j = s + 1; j < allBlocksWithNumbers.length; j++) {
			allBlocksWithNumbers[j] = center[j - allCornerBlocks.length];

			// System.out.println(allBlocksWithNumbers[j]); //// переписати з
			// //// масиву зі всіма
			// //// кутовими в заг
			// //// і додати
			// //// центральну
		}

		// }

		//// allCornerBlocks[i] =
		// System.arraycopy(corn1, 0, allCornerBlocks, 0, 4);
		// System.arraycopy(corn2, 0, allCornerBlocks, 0, 4);
		// System.arraycopy(corn3, 0, allCornerBlocks, 0, 4);
		// System.arraycopy(corn4, 0, allCornerBlocks, 0, 4);
		//
		// for (int j = 0; j < allCornerBlocks.length; j++) {
		// System.out.println(allCornerBlocks[i]);
		// }

	}

	public static void removeFromArray() {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				int currentNumber = arr[i][j];
				for (int k = 0; k < allBlocksWithNumbers.length; k++) {
					if (currentNumber == allBlocksWithNumbers[k]) {

					}
				}

			}

		}

	}

	public static void makeArrayOfWhiteNumbersDesc() {
		int k = 0;
		int ai = 0;
		int aj = 0;
		for (int i = 0; i < n * n; i++) {

			for (int j = 0; j < allBlocksWithNumbers.length; j++) {
				if (i + 1 == allBlocksWithNumbers[j]) {
					k = i + 1;
				}
				System.out.println(ai+" "+aj);
				arr[ai][aj] = k;
				// currentCell=i*n+j+1;
			}
			aj++;
			if (aj == n - 1) {

				aj = 0;
				ai++;
			}

		}

	}

	// public static int[] currentCell() {
	// int[] cell = new int[2];
	//
	// switch (currentCell) {
	// case 1:
	// cell[0] = 0;
	// cell[1] = 0;
	// break;
	// case 2:
	// cell[0] = 0;
	// cell[1] = 1;
	// break;
	// case 3:
	// cell[0] = 0;
	// cell[1] = 2;
	// break;
	// case 4:
	// cell[0] = 1;
	// cell[1] = 0;
	// break;
	// case 5:
	// cell[0] = 1;
	// cell[1] = 1;
	// break;
	// case 6:
	// cell[0] = 1;
	// cell[1] = 2;
	// break;
	// case 7:
	// cell[0] = 2;
	// cell[1] = 0;
	// break;
	// case 8:
	// cell[0] = 2;
	// cell[1] = 1;
	// break;
	// case 9:
	// cell[0] = 2;
	// cell[1] = 2;
	// break;
	// }
	//
	// return cell;
	//
	// }

}
