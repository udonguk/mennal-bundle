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
  isResultLoading = true
  result = {
    categoryType: '',
    graphType: '',
    orderNum: 0,
    factionList: [],
    resultList: []
  }

  get surveyResult () {
    if(_.isNil(this.result.resultList) || 0 === this.result.resultList.length){
      return []
    }
    return this.result.resultList
  }

  get orderNum () {
    if(_.isNil(this.orderNum) || _.isNil(this.result.orderNum)){
      return null
    }
    return this.result.orderNum
  }

  get graphType () {
    if(_.isNil(this.result) || _.isNil(this.result.graphType)){
      return null
    }
    return this.result.graphType
  }

  get barGraphFormat () {
    return this.result.factionList.map(item => {
      let faction = {}
      let totalScore = 0

      item.forEach(item2 => totalScore += item2.score)

      item.forEach(item2 => {
        faction.title = _.isNil(faction.title) ? item2.resultType : `${faction.title}|${item2.resultType}`
        faction[item2.resultType] = (item2.score / totalScore) * 10
      })
      console.debug('faction', faction)

      return faction
    })
  }

  get barRangeGraphFormat () {
    const result = this.result.factionList.map(item => {
      let faction = {}
      const totalScore = item.reduce((previous, current) => previous + current.score, 0)
      item.forEach(answer => {
        if('네' === answer.title) {
          faction.title = _.isNil(faction.title) && answer.resultType
          faction[answer.resultType] = Math.floor((answer.score / totalScore) * 10)
        }
      })
      return faction
    })

    console.log(result)

    return result
  }


  get barGraphKeys () {
    let result = []
    this.result.factionList.map(item => {
        item.forEach(item2 => result.push(item2.resultType))
    })

    console.debug('keys', result)
    return result
  }

  get barRangeGraphKeys () {
    let result = []
    this.result.factionList.map(item => {
        item.forEach(answer => {
          if('네' === answer.title){
            result.push(answer.resultType)
          }
        })
    })

    console.log('keys', result)
    return result
  }

  get graphFormat () {
    console.debug('format', this.result)
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

  sendSurvey(){
    this.isResultLoading = true
    let param = []
    this.optionLooper((item) => {
      param.push(item.resultJson)
    })
    axiosToApi.post('/survey/option', param)
      .then(res => {
        this.result = res.data
        this.isResultLoading = false
      })
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
        item.surveyItemOptions.forEach(option => handler(option))
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
}
