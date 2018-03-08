document.addEventListener("DOMContentLoaded", function () {
    var matrix = initMatrix(4);
    generateCell(matrix);
    generateCell(matrix);
    drawMatrix(matrix);


    document.addEventListener("keydown", function (e) {


        // TODO

        if (e.key === "ArrowLeft") {
            moveLeft(matrix);
            drawMatrix(matrix);
        }
        if (e.key === "ArrowRight") {
            moveRight(matrix);
            drawMatrix(matrix);
        }
        if (e.key === "ArrowUp") {
            moveUp(matrix);
            drawMatrix(matrix);
        }
        if (e.key === "ArrowDown") {
            moveDown(matrix);
            drawMatrix(matrix);
        }

    });

});


var score = 0;

function drawMatrix(matrix) {
    var area = document.querySelector("#area");

    var oldCells = document.querySelectorAll(".cell");
    if (oldCells.length > 0) {
        while (area.firstChild) {
            area.removeChild(area.firstChild);
        }
    }

    var x = 0;
    var y = 0;

    for (var i = 0; i < matrix.length; i++) {
        for (var j = 0; j < matrix.length; j++) {

            var el = document.createElement("div");
            area.appendChild(el);
            el.className = "cell cell" + matrix[i][j];

            document.querySelector("#score").innerHTML = "Score: " + score;

            if(matrix[i][j] != 0) {
                el.innerHTML = matrix[i][j];
            }

            x = j * 7 + 1.5;
            y = i * 7 + 1.5;

            el.style = "left: " + x + "vh;" + "top: " + y + "vh;";

        }
    }
}


function initMatrix(size) {
    var matrix = new Array(size);

    for (var i = 0; i < size; i++) {
        matrix[i] = new Array(size);
        for (var j = 0; j < size; j++) {
            matrix[i][j] = 0;
        }
    }

    return matrix;

}

function generateCell(matrix) {
    var freeCells = getFreeCell(matrix);
    var cell = getRandomNumberBetwean(0, freeCells.length - 1);

    var i = freeCells[cell][0];
    var j = freeCells[cell][1];

    matrix[i][j] = getRandomCellValue();
}

function getFreeCell(matrix) {
    var rez = [];
    var s = 0;
    for (var i = 0; i < matrix.length; i++) {
        for (var j = 0; j < matrix.length; j++) {
            if (matrix[i][j] == 0) {
                console.log("i= " + i + " j=" + j);
                rez[s++] = [i, j];
            }
        }
    }

    return rez;
}

function getRandomCellValue() {
    if (Math.random() < 0.9) {
        return 2;
    }
    return 4;
}


function getRandomNumberBetwean(min, max) {
    return Math.round(Math.random() * (max - min) + min);
}


function moveRight(matrix) {
    var canMove = false;
    for (var i = 0; i < matrix.length; i++) {
        var a = alignLine(matrix[i]);
        var b = mergeLine(matrix[i]);
        if(!canMove && (a || b)) {
            canMove = true;
        }
    }

    //console.log(canMove);
    if(canMove) {
        generateCell(matrix);
    }
}

function mergeLine(vector) {
    var isChanged = false;

    for(var i = vector.length - 1; i > 0; i--) {
        if(vector[i] != 0 && (vector[i] == vector[i - 1])) {
            vector[i] *= 2;
            score = vector[i] + score;
            vector[i - 1] = 0;
            isChanged = true;
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
