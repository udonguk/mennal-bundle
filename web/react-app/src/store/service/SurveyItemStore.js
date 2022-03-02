import {action, makeAutoObservable, observable, runInAction} from "mobx";
import {SurveyItemDomain} from "../domain/SurveyItemDomain";
import _ from "lodash";
import axiosToApi from "../../config/axios/api";
import {SurveyCategoryDomain} from "../domain/SurveyCategoryDomain";

export class SurveyItemStore {
  parent
  surveyItems = []
  survey = {}
  isLoading = true
  isResultLoading = false;
  result = {
    categoryType: '',
    factionList: []
  }

  get graphFormat () {
    console.debug('format', this.result.factionList.map(item => {
      return {
        score: item.score,
        faction: item.itemType
      }
    }))

    return this.result.factionList.map(item => {
      return {
        score: item.score,
        faction: item.faction
      }
    })
  }

  constructor(parent) {
    makeAutoObservable(this, {
      survey: observable,
      isLoading: observable,
      result: observable,
      loadSurveyItemsByCategory: action,
      updateSurveyItemFromServer: action,
      getSurveyItems: action,
      sendSurvey: action,
    })
    this.parent = parent
    this.loadSurveyItemsByCategory()
  }

  loadSurveyItemsByCategory(category) {
    this.isLoading = true;
    runInAction(() => {
      this.surveyItems = [];
      this.getSurveyItems(category, (res) => {
        this.survey = new SurveyCategoryDomain(this, res.data)
        this.isLoading = false
      })
    })
  }

  getSurveyItems(category, successHandler ){
    if(_.isNil(category) || _.isNil(category.id)) return

    axiosToApi.get(`/survey/${category.id}`)
      .then(res => successHandler(res))
  }

  updateSurveyItemFromServer(item) {
    let surveyItem;
    if(!surveyItem){
      surveyItem = new SurveyItemDomain(this, item)
      this.surveyItems.push(surveyItem);
    }
  }

  sendSurvey(successHandler){
    console.debug('start sendSurvy');
    let param = []
    this.itemLooper((item) => {
      param.push(item.resultJson)
    })
    console.debug('param', param)
    axiosToApi.post('/survey', param)
      .then(res => this.result = res.data)
  }

  sendSurvey2(){
    console.debug('start sendSurvy22');
    let param = []
    this.optionLooper((item) => {
      param.push(item.resultJson)
    })
    console.debug('param', param)
    axiosToApi.post('/survey/option', param)
      .then(res => this.result = res.data)
  }

  getTest() {
    axiosToApi.get('api')
      .then(res => {
        console.debug('res', res)
      })
  }

  itemLooper(handler) {
    if(_.isNil(this.survey.surveySubCategories)) return
    // console.debug('aaaaa',this.survey.surveySubCategories)
    this.survey.surveySubCategories.forEach(surveySubCategory => {
      surveySubCategory.surveyItems.forEach(item => {
        handler(item)
      })
    })
  }

  optionLooper(handler) {
    if(_.isNil(this.survey.surveySubCategories)) return
    this.survey.surveySubCategories.forEach(surveySubCategory => {
      surveySubCategory.surveyItems.forEach(item => {
        item.surveyItemOptions.forEach(option => {
          handler(option)
          }
        )
      })
    })
  }


  get checkCount(){
    let result = 0;
    this.itemLooper((item) => {
      if(item.isChecked) { result ++}
    })
    return result
  }

  get itemCount(){
    let result = 0;
    this.itemLooper((item) => result ++)
    return result
  }

  get progressPercent() {
    return this.checkCount/this.itemCount * 100
  }

//   axios.get(`${url}?${paramParser(sortedParams)}`)
// .then(res => {
// runInAction(() => {
// !this.isSameSearchHistory(this.searchValue) && this.setPage(0)
// handler(res)
// })
// })
// .catch(error => {
// if (errorHandler) {
// errorHandler(error)
// } else {
// try {
// const {message} = error['response']['data']
// const result = {status: RESULT_ERROR, message, result: false}
// console.debug('error result', result)
// } catch (e) {
// console.debug('unknown error', e)
// }
// }
// })
// .finally(() => {
// this.addSearchValueHistory(this.searchValue)
// this.setLoading(false)
// finalHandler()
// })
}