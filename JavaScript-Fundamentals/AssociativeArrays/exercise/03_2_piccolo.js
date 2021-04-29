function piccolo(allcars) {
    let carNumsMap = new Map();

    for (let car of allcars) {
        car = car.split(', ');
        let action = car.shift();
        car = car.join('');

        if (action === 'IN') {
            carNumsMap.set(car, 1);
        } else if (action == 'OUT') {
            carNumsMap.delete(car);
        }
    }

    let sortedCarsNumbers = [...carNumsMap].sort((a, b) => a[0].localeCompare(b[0]));

    if (sortedCarsNumbers.length > 0) {
        sortedCarsNumbers.forEach(e => console.log(e[0]))
    } else {
        console.log('Parking Lot is Empty');
    }
}
