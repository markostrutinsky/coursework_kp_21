import {getAuthToken} from "./authTokenInterations";

export async function addAuthHeader(headers: { [key: string]: string }): Promise<{ [key: string]: string }> {
    let accessToken = getAuthToken();
    if (accessToken && accessToken !== "undefined") {
        headers = {
            ...headers,
            Authorization: `Bearer ${accessToken}`,
        };
        const decodedToken = JSON.parse(atob(accessToken.split('.')[1]));
        const expirationTime = decodedToken.exp;
        const isTokenExpired = Date.now() >= expirationTime * 1000;
    }
    return headers;
}