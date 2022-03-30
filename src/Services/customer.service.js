import httpClient from '../http-common';

const getAll = () => {
  return httpClient.get('');
};

const get = (id) => {
  return httpClient.get('/customer',id);
};

const update = (data) => {
  return httpClient.put('', data);
};

const signin = (data) => {
  return httpClient.post('/customer/signin', data);
}

const signup = (data) => {
  return httpClient.post('/customer/signup', data);
}

export default { getAll, get, update, signin, signup };