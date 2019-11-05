(ns herbarium.db)

(def default-db-en
  {:species [{:latin "Thymus vulgaris" :name "Thyme" :aliases []}
             {:latin "Bellis perennis" :name "Common daisy" :aliases ["daisy"]}
             {:latin "Aconitum plicatum" :name "Garden monkshood" :aliases []}
             {:latin "Digitalis purpurea" :name "Common foxglove" :aliases []}
             {:latin "Mentha ×piperita L." :name "Peppermint" :aliases ["mint"]}
             {:latin "Mentha arvensis L." :name "Wild mint" :aliases ["mint"]}
             {:latin "Mentha aquatica L." :name "Water mint" :aliases []}
             ]
   :edit? true})


(def default-db-pl
  {:species [{:latin "Thymus vulgaris" :name "Macierzanka" :aliases []}
             {:latin "Bellis perennis" :name "Stokrotka pospolita" :aliases []}
             {:latin "Aconitum plicatum" :name "Tojad sudecki" :aliases []}
             {:latin "Digitalis purpurea" :name "Naparstnica purpurowa" :aliases []}
             {:latin "Mentha ×piperita L." :name "Mięta pieprzowa" :aliases []}
             {:latin "Mentha arvensis L." :name "Mięta polna" :aliases []}
             {:latin "Mentha aquatica L." :name "Mięta nadwodna" :aliases []}
             ]
   :edit? true})

(def default-db default-db-pl)
