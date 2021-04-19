function employees(arr) {

    class Employe {
        constructor(name, personalNumber) {
            this.name = name;
            this.personalNumber = personalNumber;
        }
        print() {
            return `Name: ${this.name} -- Personal Number: ${this.personalNumber}`;
        }
    }


    let employe = arr.map((name) => new Employe(name, +name.length));

    employe.forEach(e => console.log(e.print()));

}
