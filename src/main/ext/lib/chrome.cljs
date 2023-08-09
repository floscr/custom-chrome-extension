(ns ext.lib.chrome
  (:require
   [promesa.core :as p]))

(defn current-window-tabs+
  ([] (current-window-tabs+ #js {}))
  ([opts]
   (p/let [current-window (js/chrome.windows.getCurrent)
           tabs (js/chrome.tabs.query (-> (merge {:windowId (.-id current-window)} opts)
                                          (clj->js)))]
     tabs)))
