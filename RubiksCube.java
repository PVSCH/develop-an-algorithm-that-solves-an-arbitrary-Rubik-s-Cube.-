import java.util.*;

public class RubiksCube {

    private char[][] faces = new char[6][9];
    private List<String> moveHistory = new ArrayList<>();

    public RubiksCube() {
        char[] colors = {'W', 'R', 'B', 'O', 'G', 'Y'}; 
        for (int i = 0; i < 6; i++) {
            Arrays.fill(faces[i], colors[i]);
        }
    }

    private char[] rotateFaceClockwise(char[] face) {
        return new char[] {
            face[6], face[3], face[0],
            face[7], face[4], face[1],
            face[8], face[5], face[2]
        };
    }

    public void rotateFrontClockwise() {
        // Rotate face
        faces[2] = rotateFaceClockwise(faces[2]);

        // Rotate side strips
        char[] up = faces[0];
        char[] right = faces[1];
        char[] down = faces[5];
        char[] left = faces[3];

        char[] temp = {up[6], up[7], up[8]};

        up[6] = left[8];
        up[7] = left[5];
        up[8] = left[2];

        left[2] = down[0];
        left[5] = down[1];
        left[8] = down[2];

        down[0] = right[6];
        down[1] = right[3];
        down[2] = right[0];

        right[0] = temp[2];
        right[3] = temp[1];
        right[6] = temp[0];

        moveHistory.add("F");
    }

    public void scramble(int moves) {
        Random rand = new Random();
        for (int i = 0; i < moves; i++) {
            rotateFrontClockwise(); 
        }
    }

    public void solve() {
        // Just reverse the front rotations
        for (int i = moveHistory.size() - 1; i >= 0; i--) {
            rotateFrontClockwise();
        }
        moveHistory.clear();
    }

    public void display() {
        String[] faceNames = {"Up", "Right", "Front", "Left", "Back", "Down"};
        for (int i = 0; i < 6; i++) {
            System.out.println(faceNames[i] + ":");
            for (int j = 0; j < 9; j++) {
                System.out.print(faces[i][j] + " ");
                if ((j + 1) % 3 == 0) System.out.println();
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        RubiksCube cube = new RubiksCube();
        System.out.println("Initial Cube:");
        cube.display();

        cube.scramble(3);
        System.out.println("After Scrambling:");
        cube.display();

        cube.solve();
        System.out.println("After Solving:");
        cube.display();
    }
}
