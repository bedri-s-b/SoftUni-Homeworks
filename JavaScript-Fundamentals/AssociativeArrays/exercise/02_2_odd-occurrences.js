function occurences(arr) {
    let occurences = new Map();
    let myArr = arr.split(' ');
    let list = myArr.map(e => e.toLowerCase());

    for (const word of list) {
        if (!occurences.has(word)) {
            occurences.set(word, 1)
        } else {
            occurences.set(word, occurences.get(word) + 1);
        }
    }

    let result = '';

    for (const [k, v] of occurences) {
        if (v % 2 !== 0) {
            result += k + ' ';
        }
    }

    console.log(result);
}
