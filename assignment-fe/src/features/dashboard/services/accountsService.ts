export function getAccountsInfo(userId: string, authToken: string) {
    return fetch(`${process.env.REACT_APP_API_URL}/api/v1/account`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${authToken}`,
        },
        body: JSON.stringify({userId}),
    }).then(response => response.json());
}