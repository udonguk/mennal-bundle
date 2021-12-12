import axios from "axios";


const axiosToApi = axios.create({
  baseURL: 'http://www.mennal.co.kr/api',
  headers: {'X-Custom-Header': 'foobar'}
})

axiosToApi.interceptors.request.use(
  function(config){
    console.debug('config', config)
    return config;
  }
)



export default axiosToApi;