import axios from "axios";


const axiosToApi = axios.create({
  // baseURL: 'http://54.84.52.125/api',
  baseURL: 'http://www.mennal.co.kr/api',
  headers: {'X-Custom-Header': 'foobar'}
})


// axiosToApi.defaults.xsrfCookieName = 'csrftoken';
axiosToApi.defaults.xsrfCookieName = 'XSRF-TOKEN';
// axiosToApi.defaults.xsrfHeaderName = 'X-CSRFTOKEN';
axiosToApi.defaults.xsrfHeaderName = 'X-XSRF-TOKEN';

axiosToApi.interceptors.request.use(
  function(config){
    console.debug('config', config)
    return config;
  }
)



export default axiosToApi;