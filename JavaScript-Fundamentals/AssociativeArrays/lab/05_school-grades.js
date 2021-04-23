function solve(arr) {

    let students = new Map();

    for (const student of arr) {
        let [name, ...gradesInfo] = student.split(' ');
        let grades = gradesInfo.map(Number);
        if (students.has(name)) {
            grades = students.get(name).concat(grades);
        }
        students.set(name, grades);
    }

    let avr = grades => grades.reduce((a, b) => a + b) / grades.length;

    [...students.entries()].sort((a, b) => avr(a[1]) - avr(b[1]))
        .forEach(s => console.log(`${s[0]}: ${s[1].join(', ')}`))

}
