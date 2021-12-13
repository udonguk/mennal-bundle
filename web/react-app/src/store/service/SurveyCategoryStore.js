import {makeAutoObservable, runInAction} from "mobx";
import _ from "lodash";
import {SurveyCategoryDomain} from "../domain/SurveyCategoryDomain";
import axiosToApi from "../../config/axios/api";

export class SurveyCategoryStore {
  parent
  surveyCategories = []
  isLoading = true
  result = {
    'score': []
  }

  constructor(parent) {
    console.debug('SurveyCategoryStore constructor')
    makeAutoObservable(this)
    this.parent = parent
    this.loadSurveyCategories()
  }

  loadSurveyCategories() {
    this.isLoading = true;
    runInAction(async () => {
      0 === this.surveyCategories.length && this.getSurveyCategories((res) => {
        this.surveyCategories = [];
        res.data.forEach(item => {
          this.setCategoryFromServer(item);
        })
        this.isLoading = false
      });
    })
  }

  setCategoryFromServer(item) {
    let categoryItem;
    if (!categoryItem) {
      categoryItem = new SurveyCategoryDomain(this, item);
      this.surveyCategories.push(categoryItem);
    }
  }

  getSurveyCategories(func) {
    axiosToApi.get(`/survey/category`)
      .then(res => {
        func(res)
      })
  }

  getSurveyCategory(code = ''){
    if(_.isNil(code)){ return null }

    return this.surveyCategories
      .filter(item => code === item.code)[0]
  }
}