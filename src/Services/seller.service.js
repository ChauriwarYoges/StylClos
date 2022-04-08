import httpClient from '../http-common';

const getAll = () => {
  return httpClient.get('/product/list');
};

const get = (id) => {
  return httpClient.get('/user/'+id);
};

const update = (data) => {
  return httpClient.put('', data);
};

const signin = (data) => {
  return httpClient.post('/user/signin', data);
}

const signup = (data) => {
  return httpClient.post('/user/signup', data);
}

export default { getAll, get, update, signin, signup };