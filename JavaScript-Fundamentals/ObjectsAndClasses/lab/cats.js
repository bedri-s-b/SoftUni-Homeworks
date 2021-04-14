function solve(arr) {

    let cats = [];

    class Cat {
        constructor(name, age) {
            this.name = name;
            this.age = age;

        }
        meow() {
            console.log(`${this.name}, age ${this.age} says Meow`);
        }


    }

    for (let i = 0; i < arr.length; i++) {
        let catInfo = arr[i].split(' ');
        let [name, age] = [catInfo[0], catInfo[1]];
        cats.push(new Cat(name, age));
    }

    for (const cat of cats) {
        cat.meow();
    }

};

solve(['Mellow 2', 'Tom 5']);