export async function doFetchWithPost(url, jsonForBody) {
    const headersAndBody = {
        method: 'POST',
        mode: 'cors',
        headers: {
            "Content-Type": 'application/json',
        },
        body: JSON.stringify(jsonForBody)
    }

    const response = await fetch(url, headersAndBody);


    return response;


}