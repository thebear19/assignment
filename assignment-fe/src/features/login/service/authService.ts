export function login(name: string, pin: string) {
    return fetch(`${process.env.REACT_APP_API_URL}/login`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json',},
        body: JSON.stringify({name, password: pin}),
    }).then(response => response.json());
}