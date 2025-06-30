class Cube {
  constructor() {
    this.faces = {
      U: Array(9).fill('w'),
      D: Array(9).fill('y'),
      L: Array(9).fill('o'),
      R: Array(9).fill('r'),
      F: Array(9).fill('g'),
      B: Array(9).fill('b'),
    };
  }

  getCubeString() {
    return Object.values(this.faces).map(face => face.join('')).join('');
  }

  rotateFace(face, direction = 'CW') {
    if (face === 'U' && direction === 'CW') {
      this.rotateFaceClockwise('U');
      const temp = this.faces.F.slice(0, 3);
      this.faces.F.splice(0, 3, ...this.faces.R.slice(0, 3));
      this.faces.R.splice(0, 3, ...this.faces.B.slice(0, 3));
      this.faces.B.splice(0, 3, ...this.faces.L.slice(0, 3));
      this.faces.L.splice(0, 3, ...temp);
    }
  }

  rotateFaceClockwise(face) {
    const f = this.faces[face];
    const rotated = [
      f[6], f[3], f[0],
      f[7], f[4], f[1],
      f[8], f[5], f[2]
    ];
    this.faces[face] = rotated;
  }

  scramble(moves = 10) {
    const faces = ['U']; 
    const dirs = ['CW'];
    for (let i = 0; i < moves; i++) {
      const face = faces[Math.floor(Math.random() * faces.length)];
      const dir = dirs[Math.floor(Math.random() * dirs.length)];
      this.rotateFace(face, dir);
    }
  }
}

function getCubeSvg(cubeString) {
  return `<pre>${cubeString}</pre>`;
}

function displayCube(cube) {
  const cubeOutput = document.getElementById('cube-output');
  cubeOutput.innerHTML += getCubeSvg(cube.getCubeString()) + '<br>';
}

const cube = new Cube();
displayCube(cube);
cube.scramble();
displayCube(cube);

// TODO: Implement solveCube(cube);
