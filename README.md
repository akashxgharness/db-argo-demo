# EKS Config-Driven UI (Spring Boot)

This is a small Spring Boot web application whose UI theme is driven by the container image tag provided via Kubernetes/EKS configuration. The page clearly shows which environment (dev, staging, prod, default) you are viewing based on that tag.

## How it works

- The app reads an **image tag** from either:
  - an environment variable (default: `IMAGE_TAG`), or
  - a JSON file mounted into the container (default path: `/config/config.json` with an `imageTag` field).
- The tag is mapped to a **theme** (colors/logo/banner) and exposed to a simple Thymeleaf template.

Configuration is controlled via `application.yml`:

```yaml
image-tag:
  source: ENV        # or FILE
  env-var: IMAGE_TAG
  file-path: /config/config.json
```

## Sample EKS Deployment

Below is an example `Deployment` manifest that passes the container image tag to the application via an environment variable. This is usually the simplest approach.

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: eks-config-ui
spec:
  replicas: 1
  selector:
    matchLabels:
      app: eks-config-ui
  template:
    metadata:
      labels:
        app: eks-config-ui
    spec:
      containers:
        - name: eks-config-ui
          image: my-org/eks-config-ui:dev-123
          ports:
            - containerPort: 8080
          env:
            - name: IMAGE_TAG
              value: "dev-123"
---
apiVersion: v1
kind: Service
metadata:
  name: eks-config-ui
spec:
  type: ClusterIP
  selector:
    app: eks-config-ui
  ports:
    - port: 80
      targetPort: 8080
```

### Option B: Using `config.json`

Alternatively, you can manage the tag via a `ConfigMap` and `config.json`:

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: eks-config-ui-config
data:
  config.json: |
    {
      "imageTag": "staging-001"
    }
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: eks-config-ui
spec:
  replicas: 1
  selector:
    matchLabels:
      app: eks-config-ui
  template:
    metadata:
      labels:
        app: eks-config-ui
    spec:
      containers:
        - name: eks-config-ui
          image: my-org/eks-config-ui:staging-001
          ports:
            - containerPort: 8080
          volumeMounts:
            - name: config-volume
              mountPath: /config
              readOnly: true
      volumes:
        - name: config-volume
          configMap:
            name: eks-config-ui-config
```

In this mode, set in `application.yml`:

```yaml
image-tag:
  source: FILE
  file-path: /config/config.json
```

## Running locally

1. Ensure Java 17+ is installed.
2. From the project root, build and run:

```bash
mvn spring-boot:run
```

3. Provide a tag via:

```bash
IMAGE_TAG=dev-123 mvn spring-boot:run
```

Then open `http://localhost:8080` in your browser.

## Testing

- The core logic of mapping tags to themes lives in `ThemeResolver` and is covered by `ThemeResolverTests`.
- To run tests:

```bash
mvn test
```

