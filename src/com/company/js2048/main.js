
document.addEventListener("DOMContentLoaded",function() {
    var matrix = initMatrix(4);
    drawMatrix(matrix);

    document.addEventListener("keydown", function (e) {


        // TODO

        if(e.key === "ArrowLeft") {
            moveLeft(matrix);
            generetaCell(matrix);
            drawMatrix(matrix);
        }
        if(e.key === "ArrowRight") {
            moveRight(matrix);
            generetaCell(matrix);
            drawMatrix(matrix);
        }
        if(e.key === "ArrowUp") {
            moveUp(matrix);
            generetaCell(matrix);
            drawMatrix(matrix);
        }
        if(e.key === "ArrowDown") {
            moveDown(matrix);
            generetaCell(matrix);
            drawMatrix(matrix);
        }

    });

});



function drawMatrix(matrix) {
    var area = document.querySelector("#area");

    var oldCells = document.querySelectorAll(".cell");
    if (oldCells.length > 0) {
        console.log(oldCells[0]);
        while (area.firstChild) {
            area.removeChild(area.firstChild);
        }
    }

    var x = 0;
    var y = 0;

    for (var i = 0; i < matrix.length; i++) {
        x = 0;
      for (var j = 0; j < matrix.length; j++) {

          var el = document.createElement("div");

        if(matrix[i][j] == 0) {

        } else {
            el.innerHTML = matrix[i][j];
        }
        console.log(el);
        area.appendChild(el);
        el.className = "cell";
        x = j * 5 + 5;
        y = i * 5 + 5;
        el.style = "left: " + x + "vh;" + "top: " + y + "vh;";
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

  return matrix;

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
  return 2; // TODO Implement me
}


function getRandomNumberBetwean(min,max) {
  return Math.round(Math.random() * (max - min) + min);
}

function moveRight(matrix) {
    for (var i = 0; i < matrix.length; i++) {
        alignLine(matrix[i]);
        for (var j = 0; j < matrix[0].length - 1; j++) {
            if(matrix[i][j] == matrix[i][j + 1]) {
                matrix[i][j + 1] = matrix[i][j] + matrix[i][j + 1];
                matrix[i][j] = 0;
            }
        }
    }
}

function alignLine(vector) {
    for (var i = 0; i < vector.length; i++) {
        for (var j = 0; j < vector.length; j++) {
            if(vector[i] == 0) {
                var tmp = vector[i];
                vector[i] = vector[j];
                vector[j] = tmp;
            }
        }
    }
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
    var n = matrix.length;
  var l = n - 1;

  for (var i = 0; i < n; i++) {
    for (var j = 0; j < n; j++) {
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
