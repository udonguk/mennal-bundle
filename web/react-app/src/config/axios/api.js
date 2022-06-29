import axios from "axios";


const axiosToApi = axios.create({
  // baseURL: 'http://54.84.52.125/api',
  // baseURL: 'http://www.mennal.co.kr/api',
  // baseURL: 'http://192.168.30.80/api',
  // baseURL: 'http://106.244.187.76:9004/api',
  baseURL: 'http://localhost:85/api',
  // baseURL: 'http://192.168.30.80:85/api',
  headers: {'X-Custom-Header': 'foobar'}
})

axiosToApi.defaults.xsrfCookieName = 'XSRF-TOKEN';
axiosToApi.defaults.xsrfHeaderName = 'X-XSRF-TOKEN';

axiosToApi.interceptors.request.use(
  function(config){
    return config;
  }
)



export default axiosToApi;
