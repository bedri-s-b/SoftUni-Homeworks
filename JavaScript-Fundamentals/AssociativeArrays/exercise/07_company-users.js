function solve(arr) {
    let employees = {};

    arr.forEach(l => {
        let [company, idEmploy] = l.split(' -> ')
        if (!employees.hasOwnProperty(company)) {
            employees[company] = [];
            employees[company].push(idEmploy);
        } else {
            if (!employees[company].includes(idEmploy)) {
                employees[company].push(idEmploy);
            }
        }
    })

    let print = Object.entries(employees).sort((a, b) => a[0].localeCompare(b[0]))
        .forEach(e => {
            console.log(e[0]);
            e[1].forEach(id => console.log(`-- ${id}`))
        });
}

solve([
    'SoftUni -> AA12345',
    'SoftUni -> CC12344',
    'Lenovo -> XX23456',
    'SoftUni -> AA12345',
    'Movement -> DD11111'
]
);