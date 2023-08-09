(ns ext.lib.tabs
  (:require
   [promesa.core :as p]
   [ext.lib.chrome :as chrome]))

(defn close-non-selected! []
  (p/let [tabs (chrome/current-window-tabs+ {:active false
                                             :pinned false})]
    (doseq [t tabs]
      (js/chrome.tabs.remove (.-id t)))))

(defn pin-selected! []
  (p/let [[tab] (chrome/current-window-tabs+ {:active true})]
    (js/chrome.tabs.update (.-id tab) #js {:pinned (not ^js/bool (.-pinned tab))})))
