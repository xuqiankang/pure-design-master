export function getToken(key) {
	let data = localStorage.getItem(key)
	if (data) {
	 // console.log(JSON.parse(data))
		return JSON.parse(data)
	}
}

export function setToken(key, token) {
	localStorage.setItem(key, JSON.stringify(token))
}

export function removeToken(key) {
	return localStorage.removeItem(key)
}


export function getSessionToken(key) {
	let data = sessionStorage.getItem(key)
	if (data) {
		return JSON.parse(data)
	}
}

export function setSessionToken(key, token) {
	sessionStorage.setItem(key, JSON.stringify(token))
}

export function removeSessionToken(key) {
	return sessionStorage.removeItem(key)
}


export function getWeChatUser(key) {
  let data = localStorage.getItem(key)
  if (data) {
    return JSON.parse(data)
  }
}

export function setWeChatUser(key, token) {
  localStorage.setItem(key, JSON.stringify(token))
}

export function removeWeChatUser(key) {
  return localStorage.removeItem(key)
}
