function solve(input) {

    let arr = {};

    input.forEach(i => {
        let [name, number] = i.split(' ');
        arr[name] = number;
    });

    Object.keys(arr).forEach((k) => console.log(`${k} -> ${arr[k]}`));
}
