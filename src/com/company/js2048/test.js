var n = 4;
var matrix = new Array(4);

for (var i = 0; i < n; i++) {
    matrix[i] = new Array(n);
    for(var j = 0; j < n; j++) {
      matrix[i][j] = 0;
    }
}


matrix[0][0] = 2;
matrix[0][1] = 0;
matrix[0][2] = 0;
matrix[0][3] = 2;


function getMatrix(n) {
  var matrix = new Array(n);

  for (var i = 0; i < n; i++) {
      matrix[i] = new Array(n);
      for(var j = 0; j < n; j++) {
        matrix[i][j] = 0;
      }
  }

  return matrix;
}

function printMatrix(matrix) {
  for (var i = 0; i < matrix.length; i++) {
    var s = '';
    for (var j = 0; j <  matrix[0].length; j++) {
      s = s + matrix[i][j] + ' ';
    }
    console.log(s);
  }
}
// **************

function generetaCell(matrix) {
  var freeCells = getFreeCell(matrix);
  var cell = getRandomNumberBetwean(0,freeCells.length);

  var i = freeCells[cell][0];
  var j = freeCells[cell][1];

  matrix[i][j] = getRandomCellValue();
}

function getFreeCell(matrix) {
  var rez = [];
  var s = 0;
  for (var i = 0; i < matrix.length; i++) {
      for(var j = 0; j < matrix[0].length; j++) {
        if(matrix[i][j] == 0) {
          rez[s++] = [i,j];
        }
      }
  }

  return rez;
}

function getRandomCellValue() {
  return 2; // TODO change it
}


function getRandomNumberBetwean(min,max) {
  return Math.round(Math.random() * (max - min) + min);
}


function moveRight(matrix) {
    var canMove = false;
    for (var i = 0; i < matrix.length; i++) {
      if(alignLine(matrix[i]) && mergeLine(matrix[i])) {
        if(!canMove) {
          canMove = true;
        }
      }
    }

      //console.log(canMove);
      if(canMove) {
          generetaCell(matrix);
      }
}

function mergeLine(vector) {
  var isChanged = false;

  for(var i = vector.length - 1; i > 0; i--) {
    if(vector[i] != 0 && (vector[i] == vector[i - 1])) {
      vector[i] *= 2;
      vector[i - 1] = 0;
    }
  }

  alignLine(vector);
  return isChanged;
}

function alignLine(vector) {
  var isChanged = false;
  for (var i = vector.length; i > 1; i--) {
          for (var j = vector.length - 1; j > 0; j--) {
              if(vector[j] == 0 && vector[j - 1] != 0) {
                  var tmp = vector[j];
                  vector[j] = vector[j - 1];
                  vector[j - 1] = tmp;
                  isChanged = true;
              }
          }
    }
    return isChanged;
}

function moveDown(matrix) {
  rotateMatrix(matrix);
  moveRight(matrix);
  rotateMatrix(matrix);
  rotateMatrix(matrix);
  rotateMatrix(matrix);
}

function moveUp(matrix) {
  rotateMatrix(matrix);
  rotateMatrix(matrix);
  rotateMatrix(matrix);
  moveRight(matrix);
  rotateMatrix(matrix);
}

function moveLeft(matrix) {
  rotateMatrix(matrix);
  rotateMatrix(matrix);
  moveRight(matrix);
  rotateMatrix(matrix);
  rotateMatrix(matrix);
}


function rotateMatrix(matrix) {
  var clonedMatrix = copy(matrix);
  var out = getMatrix(4);

  var n = matrix.length;
  var l = n - 1;

  for (var i = 0; i < n; i++) {
    for (var j = 0; j < n; j++) {
      //var v = clonedMatrix[n - j - 1][i];
      var v = clonedMatrix[j][l];
      matrix[i][j] = v;
    }
    l--;
  }
}


function copy(o) {
   var output, v, key;
   output = Array.isArray(o) ? [] : {};
   for (key in o) {
       v = o[key];
       output[key] = (typeof v === "object") ? copy(v) : v;
   }
   return output;
}




/// ************
//printMatrix(matrix);
//console.log();
//moveRight(matrix);
//moveLeft(matrix);
//moveRight(matrix);
//var a = alignLine(matrix[0]);
//console.log(a);
//var b = mergeLine(matrix[0]);
//console.log(b);
//moveRight(matrix);
//generateCell(matrix);
//generateCell(matrix);
//rotateMatrix(matrix);
//printMatrix(matrix);
// ****

console.log(Math.random());

// *****
