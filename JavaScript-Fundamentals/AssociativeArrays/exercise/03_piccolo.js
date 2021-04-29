function solve(arr) {
    let parking = new Set();

    arr.forEach(c => {
        let current = c.split(', ')
        if (current[0] == 'IN') {
            parking.add(current[1])
        } else if (current[0] == 'OUT') {
            parking.delete(current[1])
        }
    })


    result = [...parking].sort((a, b) => {
        let f = b.match('[0-9]+')[0];
        let s = a.match('[0-9]+')[0];
        b = Number(f);
        a = Number(s);
        if (b > a) {
            return -1
        } else if (b < a) {
            return 1
        } else {
            return 0
        }
    })


    if (result.length == 0) {
        console.log('Parking Lot is Empty');
    } else {
        console.log(result.join('\n'));
    }

}
