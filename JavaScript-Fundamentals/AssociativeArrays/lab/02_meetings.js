function solve(arr) {

    let meetings = {};

    arr.forEach(m => {
        let [day, name] = m.split(' ');
        if (meetings.hasOwnProperty(day)) {
            console.log(`Conflict on ${day}!`);
        } else {
            console.log(`Scheduled for ${day}`);
            meetings[day] = name;
         }

    })

    Object.keys(meetings).forEach(m => console.log(`${m} -> ${meetings[m]}`))
}
