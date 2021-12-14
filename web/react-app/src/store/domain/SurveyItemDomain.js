import {action, makeAutoObservable, observable, reaction} from "mobx";
import _ from "lodash";
import {SurveyItemOptionDomain} from "./SurveyItemOptionDomain";

export class SurveyItemDomain {
  id = ""
  title = ""
  orderNum = ""
  useYn = ""
  regDt = null
  editDt = null
  delDt = null
  surveyItemOptions = []

  _isChecked = false
  status = ""

  store = null
  saveHandler = null

  constructor(store, item) {
    makeAutoObservable(this, {
      id: observable,
      surveyItemOptions: observable,
      _isChecked: observable,
      setSurveyItemOptions: action
    })
    this.store = store

    this.id = item.id
    this.title = item.title
    this.orderNum = item.orderNum
    this.useYn = item.useYn
    this.regDt = item.regDt
    this.editDt = item.editDt
    this.delDt = item.delDt

    this.setSurveyItemOptions(item);
  }

  get isChecked () {
    let result = false
    if(0 === this.surveyItemOptions.length) result = false

    this.surveyItemOptions.forEach(option => {
      if(option.isChecked) result = true
    })
    return result
  }

  setSurveyItemOptions(item) {
    this.surveyItemOptions = []
    if (!_.isNil(item.surveyItemOptionEntities)) {
      item.surveyItemOptionEntities.forEach(
        surveyItemOption => this.surveyItemOptions.push(new SurveyItemOptionDomain(this, surveyItemOption)))
    }
  }
}