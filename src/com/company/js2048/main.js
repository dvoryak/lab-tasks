
document.addEventListener("DOMContentLoaded",function() {

});



function drawMatrix(matrix) {
    var area = document.querySelector("area");

    for (var i = 0; i < matrix.length; i++) {
      for (var j = 0; j < matrix.length; j++) {
        if(matrix[i][j] == 0) {

        } else {

        }
        var el = document.createElement("div");
        
      }
    }
}


function initMatrix(size) {
  var matrix = new Array(size);

  for (var i = 0; i < size; i++) {
      matrix[i] = new Array(size);
      for(var j = 0; j < size; j++) {
        matrix[i][j] = 0;
      }
  }

}

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
  for (var i = 0; i < matrix.length; i++) {

    for (var j = 0; j < matrix[0].length - 1; j++) {
      if(matrix[i][j] != 0) {
        if(matrix[i][j] == matrix[i][j+1]) {
          matrix[i][j+1] = matrix[i][j] + matrix[i][j+1];
          matrix[i][j] = 0;
          j++;
          alignLine(matrix[i]);
        }
      }
    }

  }
}

function alignLine(vector) {
  for (var i = 0; i < vector.length - 1; i++) {
    if(vector[i] != 0) {
      if(vector[i + 1] == 0) {
        var tmp = vector[i];
        vector[i] = vector[i + 1];
        vector[i + 1] = tmp;
      }
    }
  }
}

function moveUp(matrix) {
  rotateMatrix(matrix);
  moveRight(matrix);
  rotateMatrix(matrix);
  rotateMatrix(matrix);
  rotateMatrix(matrix);
}

function moveDown(matrix) {
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
