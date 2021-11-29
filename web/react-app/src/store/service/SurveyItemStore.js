import {makeAutoObservable, runInAction} from "mobx";
import axios from "axios";
import {getSurveyItems} from "../../dump/surveyItemDump";
import {SurveyItem} from "../domain/SurveyItem";

export class SurveyItemStore {
  parent
  surveyItems = []
  isLoading = true

  constructor(parent) {
    makeAutoObservable(this)
    this.parent = parent
    this.loadSurveyItems()
  }

  loadSurveyItems() {
    this.isLoading = true;
    runInAction(() => {
      getSurveyItems().forEach(item => this.updateSurveyItemFromServer(item))
      this.isLoading = false
    })
  }

  updateSurveyItemFromServer(item) {
    let surveyItem;
    if(!surveyItem){
      surveyItem = new SurveyItem(this, item)
      this.surveyItems.push(surveyItem);
    }
  }

  getTest() {
    axios.get('api')
      .then(res => {
        console.debug('res', res)
      })
  }

  get checkCount(){
    return this.surveyItems.filter(item =>
      true === item.isChecked
    ).length
  }

  get itemCount(){
    return this.surveyItems.length
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