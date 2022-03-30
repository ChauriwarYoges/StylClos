import httpClient from '../http-common';

const getAll = () => {
  return httpClient.get('');
};

const get = (id) => {
  return httpClient.get(id);
};

const update = (data) => {
  return httpClient.put('', data);
};

const signin = (data) => {
  return httpClient.post('/seller/signin', data);
}

const signup = (data) => {
  return httpClient.post('/seller/signup', data);
}

export default { getAll, get, update, signin, signup };