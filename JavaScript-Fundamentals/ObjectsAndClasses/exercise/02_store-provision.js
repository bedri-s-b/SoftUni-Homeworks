function solve(arr1, arr2) {

    let provisions = {};

    function readArr(arr) {
        for (let i = 0; i < arr.length; i += 2) {
            if (!provisions.hasOwnProperty(arr[i])) {
                provisions[arr[i]] = Number(arr[i + 1]);
            } else {
                let key = arr[i];
                provisions[key] = Number(provisions[key]) + Number(arr[i + 1]);
            }
        }
    }

    readArr(arr1);
    readArr(arr2);

    Object.keys(provisions).forEach(k => {
       console.log(`${k} -> ${provisions[k]}`);
   })
}
