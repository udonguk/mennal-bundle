import {action, makeAutoObservable, observable} from "mobx";
import _ from "lodash";
import {SurveySubCategory} from "./SurveySubCategory";

export class SurveyCategory {
  id = ""
  title = ""
  code = ""
  useYn = ""
  regDt = null
  editDt = null
  delDt = null
  surveySubCategories = []

  store = null
  saveHandler = null

  constructor(store, item) {
    makeAutoObservable(this, {
      id: observable,
      surveySubCategories: observable,
      setSubCategories: action,
    })
    this.store = store

    this.id = item.id
    this.title = item.title
    this.code = item.code
    this.useYn = item.useYn
    this.regDt = item.regDt
    this.editDt = item.editDt
    this.delDt = item.delDt

    this.setSubCategories(item);
  }

  setSubCategories(item) {
    this.surveySubCategories = []
    if (!_.isNil(item.surveySubCategories)) {
      item.surveySubCategories.forEach(
        subCategory => this.surveySubCategories.push(new SurveySubCategory(this, subCategory)))
    }
  }
}